package utils;

import models.Class;
import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Validating {

    public static boolean validateRole(String role) {
        Connection db;
        PreparedStatement ps;
        Role dbRole = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT name FROM norges_roforbund.account_role WHERE name = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, role);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbRole = new Role();
                dbRole.setName(rs.getString("name"));
            }
            if (dbRole != null) {
                return dbRole.getName().equals(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateClub(String club) {
        Connection db;
        PreparedStatement ps;
        Club dbClub = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT name FROM norges_roforbund.account_club WHERE name = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, club);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbClub = new Club();
                dbClub.setName(rs.getString("name"));
            }
            if (dbClub != null) {
                return dbClub.getName().equals(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        Connection db;
        PreparedStatement ps;
        User dbUser = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT email FROM norges_roforbund.account_user WHERE email = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbUser = new User();
                dbUser.setEmail(rs.getString("email"));
            }
            if (dbUser != null) {
                return dbUser.getEmail().equals(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validatePassword(String password1, String password2) {
        return password1.equals(password2);
    }


    public static boolean validateUser(String email, String password) {
        Connection db;
        PreparedStatement ps;
        User dbUser = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT email, password FROM norges_roforbund.account_user WHERE email = ? AND password = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbUser = new User();
                dbUser.setEmail(rs.getString("email"));
                dbUser.setPassword(rs.getString("password"));
            }
            if (dbUser != null) {
                if (dbUser.getEmail().equals(email)) {
                    return dbUser.getPassword().equals(password);
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateClass(String class_) {
        Connection db;
        PreparedStatement ps;
        Class dbClass = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT name FROM norges_roforbund.registry_class WHERE name = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, class_);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbClass = new Class();
                dbClass.setName(rs.getString("name"));
            }
            if (dbClass != null) {
                return dbClass.getName().equals(class_);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateTest(String test) {
        Connection db;
        PreparedStatement ps;
        Test dbTest = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT name FROM norges_roforbund.registry_test WHERE name = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, test);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbTest = new Test();
                dbTest.setName(rs.getString("name"));
            }
            if (dbTest != null) {
                return dbTest.getName().equals(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateWeek(int week) {
        Connection db;
        PreparedStatement ps;
        Week dbWeek = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT number FROM norges_roforbund.registry_week WHERE number = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setInt(1, week);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbWeek = new Week();
                dbWeek.setNumber(rs.getInt("number"));
            }
            if (dbWeek != null) {
                return dbWeek.getNumber() == week;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateTestdata(int year, int week_id, int class_id, int utøver_id) {
        Connection db;
        PreparedStatement ps;
        Testdata dbTestdata = null;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT year, week_id, class_id, utøver_id  FROM norges_roforbund.registry_testdata WHERE year = ? AND week_id = ? AND class_id = ? AND utøver_id = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setInt(1, year);
            ps.setInt(2, week_id);
            ps.setInt(3, class_id);
            ps.setInt(4, utøver_id);
            rs = ps.executeQuery();

            while (rs.next()) {
                dbTestdata = new Testdata();
                dbTestdata.setYear(rs.getInt("year"));

                Week week = new Week();
                week.setId(rs.getInt("week_id"));
                dbTestdata.setWeek_id(week);

                Class class_ = new Class();
                class_.setId(rs.getInt("class_id"));
                dbTestdata.setClass_id(class_);

                User utøver = new User();
                utøver.setId(rs.getInt("utøver_id"));
                dbTestdata.setUtøver_id(utøver);
            }
            if (dbTestdata != null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}

