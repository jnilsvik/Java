package servlets;

import models.Testdata;
import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utils.DbQueries.listAllTests;
import static utils.SessionRetrieval.getSessionTestdata;
import static utils.SessionRetrieval.getSessionUser;


@WebServlet(name = "TestdataResultCreate", urlPatterns = {"/TestdataResultCreate"})
public class TestdataResultCreate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id == 2) {
            request.setAttribute("testList", listAllTests());
            request.getRequestDispatcher("testdataResultCreate.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        int test_id = Integer.parseInt(request.getParameter("test_id"));

        Connection db = null;
        PreparedStatement ps;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();

            String query;
            if (request.getParameter("test_result").equals("")) {
                query = "INSERT INTO norges_roforbund.registry_testdataresult (test_time, test_id, testdata_id) values (?,?,?)";
            } else {
                query = "INSERT INTO norges_roforbund.registry_testdataresult (test_result, test_id, testdata_id) values (?,?,?)";
            }
            ps = db.prepareStatement(query);

            if (request.getParameter("test_result").equals("")) {
                ps.setString(1, request.getParameter("test_time"));
            } else {
                ps.setFloat(1, Float.parseFloat(request.getParameter("test_result")));
            }

            ps.setInt(2, test_id);

            if (request.getParameter("id").equals("null")) {
                Testdata sessionTestdata = getSessionTestdata(request);
                int testdata_id = sessionTestdata.getId();
                ps.setInt(3, testdata_id);
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                ps.setInt(3, id);
            }


            ps.execute();
            response.sendRedirect("TestdataResultCreate?message=" + URLEncoder.encode("Testdata Result is successfully created!", "UTF-8"));
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                assert db != null;
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
