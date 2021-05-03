package servlets;

import models.Club;
import models.Role;
import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

import static utils.SessionRetrieval.getSessionUser;


@WebServlet(name = "UserList", urlPatterns = {"/UserList"})
public class UserList extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");

        Connection db = null;
        PreparedStatement ps;
        ArrayList<User> userList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query;
            User sessionUser = getSessionUser(request);
            int sessionRole_id = sessionUser.getRole_id().getId();
            if (sessionRole_id == 1) {
                query = "SELECT account_user.id as user_id, image, first_name, last_name, email, account_club.id as club_id, account_club.name as club_name FROM norges_roforbund.account_user INNER JOIN norges_roforbund.account_club ON account_user.club_id = account_club.id WHERE role_id = 2 ORDER BY club_name, first_name, last_name";
            } else {
                int sessionClub_id = sessionUser.getClub_id().getId();
                query = "SELECT account_user.id as user_id, image, first_name, last_name, email, account_club.id as club_id, account_club.name as club_name, account_role.id as role_id, account_role.name as role_name FROM ((norges_roforbund.account_user INNER JOIN norges_roforbund.account_club ON account_user.club_id = account_club.id INNER JOIN norges_roforbund.account_role ON account_user.role_id = account_role.id)) WHERE club_id = " + sessionClub_id + " ORDER BY role_id, first_name, last_name";
            }
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("user_id"));

                Blob blob = rs.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                byte[] imageBytes = outputStream.toByteArray();
                String image = Base64.getEncoder().encodeToString(imageBytes);
                inputStream.close();
                outputStream.close();
                user.setImage(image);

                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));

                Club club = new Club();
                club.setId(rs.getInt("club_id"));
                club.setName(rs.getString("club_name"));
                user.setClub_id(club);

                if (sessionRole_id != 1) {
                    Role role = new Role();
                    role.setId(rs.getInt("role_id"));
                    role.setName(rs.getString("role_name"));
                    user.setRole_id(role);
                }
                userList.add(user);
            }
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("userList.jsp").forward(request, response);
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
