package servlets;

import models.Role;
import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.DbQueries.listAllRoles;
import static utils.SessionRetrieval.getSessionUser;


@WebServlet(name = "UserUpdate", urlPatterns = {"/UserUpdate"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 2)
public class UserUpdate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id != 3) {
            int id = Integer.parseInt(request.getParameter("id"));

            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "SELECT account_user.id as user_id, account_role.id as role_id, account_role.name as role_name FROM norges_roforbund.account_user INNER JOIN norges_roforbund.account_role ON account_user.role_id = account_role.id WHERE account_user.id = ?";
                ps = db.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs;
                rs = ps.executeQuery();
                rs.next();

                User user = new User();

                user.setId(rs.getInt("user_id"));

                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setName(rs.getString("role_name"));
                user.setRole_id(role);

                request.setAttribute("user", user);
                request.setAttribute("roleList", listAllRoles());
                request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
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

        int role = Integer.parseInt(request.getParameter("role"));

        Connection db = null;
        PreparedStatement ps;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "Update norges_roforbund.account_user SET role_id = ? WHERE id = ?";
            ps = db.prepareStatement(query);

            ps.setInt(1, role);
            ps.setInt(2, id);

            ps.execute();
            response.sendRedirect("UserUpdate?id=" + id + "&message=" + URLEncoder.encode("Account is successfully updated!", "UTF-8"));
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
