package servlets;

import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utils.SessionRetrieval.getSessionUser;
import static utils.Validating.validateEmail;


@WebServlet(name = "UserProfile", urlPatterns = {"/UserProfile"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class UserProfile extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");

        User sessionUser = getSessionUser(request);
        int sessionUserId = sessionUser.getId();

        if (!validateEmail(email) || email.equals(sessionUser.getEmail())) {

            Connection db = null;
            PreparedStatement ps;

            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "Update norges_roforbund.account_user SET email = ? WHERE id = " + sessionUserId + "";
                ps = db.prepareStatement(query);

                ps.setString(1, email);

                ps.execute();
                HttpSession session = request.getSession();
                session.setAttribute("email", email);

                User user = getSessionUser(request);
                session.setAttribute("user", user);

                response.sendRedirect("UserProfile?message=" + URLEncoder.encode("Email is successfully updated!", "UTF-8"));
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
            response.sendRedirect("UserProfile?errorMessage=" + URLEncoder.encode("Email is already in use!", "UTF-8"));
        }
    }
}
