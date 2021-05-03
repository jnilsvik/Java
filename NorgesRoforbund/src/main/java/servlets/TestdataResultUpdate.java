package servlets;

import models.Test;
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


@WebServlet(name = "TestdataResultUpdate", urlPatterns = {"/TestdataResultUpdate"})
public class TestdataResultUpdate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id == 2) {

            int id = Integer.parseInt(request.getParameter("id"));

            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "SELECT registry_testdataresult.id as registry_testdataresult_id, test_result, test_time,test_id, registry_test.name as test_name, testdata_id FROM norges_roforbund.registry_testdataresult INNER JOIN norges_roforbund.registry_test ON registry_testdataresult.test_id = registry_test.id WHERE registry_testdataresult.id = ?";
                ps = db.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs;
                rs = ps.executeQuery();
                rs.next();

                TestdataResult testdataResult = new TestdataResult();

                testdataResult.setId(rs.getInt("registry_testdataresult_id"));
                testdataResult.setTest_result(rs.getFloat("test_result"));
                testdataResult.setTest_time(rs.getString("test_time"));

                Testdata testdata = new Testdata();
                testdata.setId(rs.getInt("testdata_id"));
                testdataResult.setTestdata_id(testdata);

                Test test = new Test();
                test.setId(rs.getInt("test_id"));
                test.setName(rs.getString("test_name"));
                testdataResult.setTest_id(test);

                request.setAttribute("testdataResult", testdataResult);
                request.getRequestDispatcher("testdataResultUpdate.jsp").forward(request, response);
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
            String query;
            if (request.getParameter("test_time").equals("null")) {
                query = "Update norges_roforbund.registry_testdataresult SET test_result = ? WHERE id = ?";
            } else {
                query = "Update norges_roforbund.registry_testdataresult SET test_time = ? WHERE id = ?";
            }
            ps = db.prepareStatement(query);

            if (request.getParameter("test_time").equals("null")) {
                ps.setFloat(1, Float.parseFloat(request.getParameter("test_result")));
            } else {
                ps.setString(1, request.getParameter("test_time"));
            }

            ps.setInt(2, id);

            ps.execute();
            response.sendRedirect("TestdataResultDetails?id=" + testdata_id + "&message=" + URLEncoder.encode("Testdata result is successfully updated!", "UTF-8"));
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
