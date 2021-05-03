package servlets;

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

import static utils.Validating.validateTest;


@WebServlet(name = "TestCreate", urlPatterns = {"/TestCreate"})
public class TestCreate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("testCreate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name").toLowerCase();

        int has_time_;
        if(request.getParameter("has_time") == null){
            has_time_ = 0;
        }else{
            has_time_ = 1;
        }

        if (!validateTest(name)) {
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "INSERT INTO norges_roforbund.registry_test (name, has_time) values (?,?)";
                ps = db.prepareStatement(query);
                ps.setString(1, name);
                ps.setInt(2, has_time_);

                ps.execute();
                response.sendRedirect("TestCreate?message=" + URLEncoder.encode("Test is successfully created!", "UTF-8"));
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
        } else {
            response.sendRedirect("testCreate.jsp?errorMessage=" + URLEncoder.encode("Test already in use!", "UTF-8"));
        }
    }
}
