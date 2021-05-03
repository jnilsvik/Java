package servlets;

import models.Testdata;
import models.TestdataResult;
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
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.SessionRetrieval.getSessionUser;


@WebServlet(name = "TestdataResultDelete", urlPatterns = {"/TestdataResultDelete"})
public class TestdataResultDelete extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id == 2) {
            int id = Integer.parseInt(request.getParameter("id"));
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "SELECT id, testdata_id FROM norges_roforbund.registry_testdataresult WHERE registry_testdataresult.id = ?";
                ps = db.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs;
                rs = ps.executeQuery();
                rs.next();

                TestdataResult testdataResult = new TestdataResult();

                testdataResult.setId(rs.getInt("id"));

                Testdata testData = new Testdata();
                testData.setId(rs.getInt("testdata_id"));
                testdataResult.setTestdata_id(testData);

                request.setAttribute("testdataResult", testdataResult);
                request.getRequestDispatcher("testdataResultDelete.jsp").forward(request, response);
                rs.close();

            } catch (SQLException | ServletException e) {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        int testdata_id = Integer.parseInt(request.getParameter("testdata_id"));

        Connection db = null;
        PreparedStatement ps;

        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "DELETE FROM norges_roforbund.registry_testdataresult WHERE id = ?";
            ps = db.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            response.sendRedirect("TestdataResultDetails?id=" + testdata_id + "&message=" + URLEncoder.encode("Account is successfully deleted!", "UTF-8"));
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
