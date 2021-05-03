package servlets;

import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static utils.SessionRetrieval.getSessionUser;


@WebServlet(name = "UserProfileImage", urlPatterns = {"/UserProfileImage"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class UserProfileImage extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        InputStream image = null;
        Part filePart = request.getPart("image");
        if (filePart != null) {
            image = filePart.getInputStream();
        }

        User sessionUser = getSessionUser(request);
        int sessionUserId = sessionUser.getId();

        Connection db = null;
        PreparedStatement ps;

        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "Update norges_roforbund.account_user SET image = ? WHERE id = " + sessionUserId + "";
            ps = db.prepareStatement(query);

            if (image != null) {
                ps.setBlob(1, image);
            }

            ps.execute();

            HttpSession session = request.getSession();
            User user = getSessionUser(request);
            session.setAttribute("user", user);

            response.sendRedirect("UserProfile?message=" + URLEncoder.encode("Profile picture is successfully updated!", "UTF-8"));
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
