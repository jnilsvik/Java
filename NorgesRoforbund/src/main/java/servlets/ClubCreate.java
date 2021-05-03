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

import static utils.Validating.validateClub;


@WebServlet(name = "ClubCreate", urlPatterns = {"/ClubCreate"})
public class ClubCreate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("clubCreate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name").toLowerCase();
        if (!validateClub(name)) {
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "INSERT INTO norges_roforbund.account_club (name) values (?)";
                ps = db.prepareStatement(query);
                ps.setString(1, name);

                ps.execute();
                response.sendRedirect("ClubCreate?message=" + URLEncoder.encode("Club is successfully created!", "UTF-8"));
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
            response.sendRedirect("clubCreate.jsp?errorMessage=" + URLEncoder.encode("Club already in use!", "UTF-8"));
        }
    }
}
