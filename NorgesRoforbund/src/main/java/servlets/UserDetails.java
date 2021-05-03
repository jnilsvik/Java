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
import java.util.Base64;


@WebServlet(name = "UserDetails", urlPatterns = {"/UserDetails"})
public class UserDetails extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));

        Connection db = null;
        PreparedStatement ps;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT account_user.id as user_id, image, first_name, last_name, email, year_of_birth, account_club.id as club_id, account_club.name as club_name, account_role.id as role_id, account_role.name as role_name FROM ((norges_roforbund.account_user INNER JOIN norges_roforbund.account_club ON account_user.club_id = account_club.id INNER JOIN norges_roforbund.account_role ON account_user.role_id = account_role.id)) WHERE account_user.id = ?";
            ps = db.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            rs.next();

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
            user.setYear_of_birth(rs.getInt("year_of_birth"));

            Club club = new Club();
            club.setId(rs.getInt("club_id"));
            club.setName(rs.getString("club_name"));
            user.setClub_id(club);

            Role role = new Role();
            role.setId(rs.getInt("role_id"));
            role.setName(rs.getString("role_name"));
            user.setRole_id(role);

            request.setAttribute("user", user);
            request.getRequestDispatcher("userDetails.jsp").forward(request, response);
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
