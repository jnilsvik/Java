package servlets;

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


@WebServlet(name = "UserDelete", urlPatterns = {"/UserDelete"})
public class UserDelete extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id != 3) {
            int id = Integer.parseInt(request.getParameter("id"));
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "SELECT id FROM norges_roforbund.account_user WHERE account_user.id = ?";
                ps = db.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs;
                rs = ps.executeQuery();
                rs.next();

                User user = new User();

                user.setId(rs.getInt("id"));

                request.setAttribute("user", user);
                request.getRequestDispatcher("userDelete.jsp").forward(request, response);
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

        Connection db = null;
        PreparedStatement ps;

        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "DELETE FROM norges_roforbund.account_user WHERE id = ?";
            ps = db.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            response.sendRedirect("UserList?message=" + URLEncoder.encode("Account is successfully deleted!", "UTF-8"));
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
