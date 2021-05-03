package servlets;

import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.DbQueries.listAllClubs;
import static utils.DbQueries.listAllRoles;
import static utils.PasswordEncrypting.encrypt;
import static utils.SessionRetrieval.getSessionUser;
import static utils.Validating.validateEmail;
import static utils.Validating.validatePassword;


@WebServlet(name = "UserCreate", urlPatterns = {"/UserCreate"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class UserCreate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id != 3) {
        request.setAttribute("clubList", listAllClubs());
        request.setAttribute("roleList", listAllRoles());
        request.getRequestDispatcher("userCreate.jsp").forward(request, response);
    }
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

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = (request.getParameter("email")).toLowerCase();
        int year_of_birth = Integer.parseInt(request.getParameter("year_of_birth"));
        int role = Integer.parseInt(request.getParameter("role"));
        String password1 = encrypt(request.getParameter("password1"));
        String password2 = encrypt(request.getParameter("password2"));

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_joined = sdf.format(date);


        if (!validateEmail(email) && validatePassword(password1, password2)) {
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "INSERT INTO norges_roforbund.account_user (image, first_name, last_name, email, year_of_birth, club_id, role_id, password, date_joined) values (?,?,?,?,?,?,?,?,?)";
                ps = db.prepareStatement(query);
                if (image != null) {
                    ps.setBlob(1, image);
                }
                ps.setString(2, first_name);
                ps.setString(3, last_name);
                ps.setString(4, email);
                ps.setInt(5, year_of_birth);

                if (request.getParameterMap().containsKey("club")) {
                    ps.setInt(6, Integer.parseInt(request.getParameter("club")));
                } else {
                    User user = getSessionUser(request);
                    ps.setInt(6, user.getClub_id().getId());
                }

                ps.setInt(7, role);
                ps.setString(8, password1);
                ps.setString(9, date_joined);

                ps.execute();
                response.sendRedirect("UserList?message=" + URLEncoder.encode("Account is successfully created!", "UTF-8"));

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
        } else if (validateEmail(email) && validatePassword(password1, password2)) {
            response.sendRedirect("UserCreate?errorMessage=" + URLEncoder.encode("Email already in use!", "UTF-8"));
        } else if (!validateEmail(email) && !validatePassword(password1, password2)) {
            response.sendRedirect("UserCreate?errorMessage=" + URLEncoder.encode("Passwords must match!", "UTF-8"));
        } else {
            response.sendRedirect("UserCreate?errorMessage=" + URLEncoder.encode("Email already in use and Passwords must match!", "UTF-8"));
        }
    }
}
