package utils;

import models.Club;
import models.Role;
import models.Testdata;
import models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;

import static utils.DbQueries.getSuperuserEmail;


public class SessionRetrieval {

    public static User getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionEmail = (String) session.getAttribute("email");

        Connection db;
        PreparedStatement ps;
        User user = new User();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            ResultSet rs;
            String query;
            if (sessionEmail.equals(getSuperuserEmail())) {
                query = "SELECT account_user.id as user_id, image, first_name, last_name, email, year_of_birth, date_joined, account_role.id as role_id, account_role.name as role_name FROM norges_roforbund.account_user INNER JOIN norges_roforbund.account_role ON account_user.role_id = account_role.id WHERE email = ?";
            } else {
                query = "SELECT account_user.id as user_id, image, first_name, last_name, email, year_of_birth, date_joined, account_club.id as club_id, account_club.name as club_name, account_role.id as role_id, account_role.name as role_name FROM ((norges_roforbund.account_user INNER JOIN norges_roforbund.account_club ON account_user.club_id = account_club.id INNER JOIN norges_roforbund.account_role ON account_user.role_id = account_role.id)) WHERE email = ?";
            }
            ps = db.prepareStatement(query);
            ps.setString(1, sessionEmail);
            rs = ps.executeQuery();
            rs.next();
            user.setId(rs.getInt("id"));

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
            user.setDate_joined(rs.getString("date_joined"));

            Role role = new Role();
            role.setId(rs.getInt("role_id"));
            role.setName(rs.getString("role_name"));
            user.setRole_id(role);

            if (!sessionEmail.equals(getSuperuserEmail())) {
                Club club = new Club();
                club.setId(rs.getInt("club_id"));
                club.setName(rs.getString("club_name"));
                user.setClub_id(club);
            }

            return user;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Testdata getSessionTestdata(HttpServletRequest request) {

        HttpSession session = request.getSession();
        int year = (int) session.getAttribute("year");
        int week_id = (int) session.getAttribute("week_id");
        int class_id = (int) session.getAttribute("class_id");
        int utøver_id = (int) session.getAttribute("utøver_id");

        Connection db;
        PreparedStatement ps;
        Testdata testdata = new Testdata();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            ResultSet rs;
            String query = "SELECT id FROM norges_roforbund.registry_testdata WHERE year = ? AND week_id = ? AND class_id = ? AND utøver_id = ?";
            ps = db.prepareStatement(query);
            ps.setInt(1, year);
            ps.setInt(2, week_id);
            ps.setInt(3, class_id);
            ps.setInt(4, utøver_id);

            rs = ps.executeQuery();
            rs.next();

            testdata.setId(rs.getInt("id"));
            return testdata;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testdata;
    }

}
