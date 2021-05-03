package servlets;

import models.Test;
import models.TestdataResult;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "TestdataResultDetails", urlPatterns = {"/TestdataResultDetails"})
public class TestdataResultDetails extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        Connection db = null;
        PreparedStatement ps;
        ArrayList<TestdataResult> testdataResultList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT registry_testdataresult.id as testdataResult_id, test_result, test_time, test_id, registry_test.name as test_name FROM norges_roforbund.registry_testdata INNER JOIN norges_roforbund.registry_testdataresult ON registry_testdata.id = registry_testdataresult.testdata_id INNER JOIN norges_roforbund.registry_test ON registry_testdataresult.test_id = registry_test.id WHERE testdata_id = ?";
            ps = db.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {

                TestdataResult testdataResult = new TestdataResult();

                testdataResult.setId(rs.getInt("testdataResult_id"));
                testdataResult.setTest_result(rs.getFloat("test_result"));
                testdataResult.setTest_time(rs.getString("test_time"));

                Test test = new Test();
                test.setId(rs.getInt("test_id"));
                test.setName(rs.getString("test_name"));
                testdataResult.setTest_id(test);

                testdataResultList.add(testdataResult);
            }
            request.setAttribute("testdataResultList", testdataResultList);
            request.getRequestDispatcher("testdataResultDetails.jsp").forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
