package utils;

import models.Class;
import models.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.SessionRetrieval.getSessionUser;

public class DbQueries {

    public static String getSuperuserEmail() {
        Connection db;
        PreparedStatement ps;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            ResultSet rs;
            String query = "SELECT email FROM norges_roforbund.account_user WHERE role_id = 1";
            ps = db.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            return rs.getString("email");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static ArrayList<Role> listAllRoles() {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<Role> roleList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT * FROM norges_roforbund.account_role WHERE id != 1";
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                roleList.add(role);
            }
            rs.close();

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
        return roleList;
    }

    public static ArrayList<User> listAllUsers(HttpServletRequest request) {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<User> userList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            User sessionUser = getSessionUser(request);
            int sessionClub_id = sessionUser.getClub_id().getId();
            String query = "SELECT id, first_name, last_name FROM norges_roforbund.account_user WHERE role_id = 3 AND club_id =" + sessionClub_id + "";
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                User utøver = new User();
                utøver.setId(rs.getInt("id"));
                utøver.setFirst_name(rs.getString("first_name"));
                utøver.setLast_name(rs.getString("last_name"));
                userList.add(utøver);
            }
            rs.close();

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
        return userList;
    }


    public static ArrayList<Club> listAllClubs() {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<Club> clubList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT * FROM norges_roforbund.account_club";
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Club club = new Club();
                club.setId(rs.getInt("id"));
                club.setName(rs.getString("name"));
                clubList.add(club);
            }
            rs.close();

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
        return clubList;
    }

    public static ArrayList<Class> listAllClasses() {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<Class> classList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT * FROM norges_roforbund.registry_class";
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Class class_ = new Class();
                class_.setId(rs.getInt("id"));
                class_.setName(rs.getString("name"));
                classList.add(class_);
            }
            rs.close();

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
        return classList;
    }

    public static ArrayList<Week> listAllWeeks() {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<Week> weekList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT * FROM norges_roforbund.registry_week";
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Week week = new Week();
                week.setId(rs.getInt("id"));
                week.setNumber(rs.getInt("number"));
                weekList.add(week);
            }
            rs.close();

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
        return weekList;
    }

    public static ArrayList<Test> listAllTests() {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<Test> testList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT * FROM norges_roforbund.registry_test";
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Test test = new Test();
                test.setId(rs.getInt("id"));
                test.setName(rs.getString("name"));
                testList.add(test);
            }
            rs.close();

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
        return testList;
    }

}
