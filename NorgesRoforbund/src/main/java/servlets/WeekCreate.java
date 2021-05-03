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

import static utils.Validating.validateWeek;


@WebServlet(name = "WeekCreate", urlPatterns = {"/WeekCreate"})
public class WeekCreate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("weekCreate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        int number = Integer.parseInt(request.getParameter("number"));
        if (!validateWeek(number)) {
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "INSERT INTO norges_roforbund.registry_week (number) values (?)";
                ps = db.prepareStatement(query);
                ps.setInt(1, number);

                ps.execute();
                response.sendRedirect("WeekCreate?message=" + URLEncoder.encode("Week is successfully created!", "UTF-8"));
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
            response.sendRedirect("weekCreate.jsp?errorMessage=" + URLEncoder.encode("Week already in use!", "UTF-8"));
        }
    }
}
