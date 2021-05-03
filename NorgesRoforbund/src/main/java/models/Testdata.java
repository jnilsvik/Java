package models;

import utils.DbTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Testdata {
    private int id;
    private int year;
    private String date_inserted;
    private String date_updated;
    private Class class_id;
    private Club club_id;
    private User utøver_id;
    private Week week_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDate_inserted() {
        return date_inserted;
    }

    public void setDate_inserted(String date_inserted) {
        this.date_inserted = date_inserted;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public Class getClass_id() {
        return class_id;
    }

    public void setClass_id(Class class_id) {
        this.class_id = class_id;
    }

    public Club getClub_id() {
        return club_id;
    }

    public void setClub_id(Club club_id) {
        this.club_id = club_id;
    }

    public User getUtøver_id() {
        return utøver_id;
    }

    public void setUtøver_id(User utøver_id) {
        this.utøver_id = utøver_id;
    }

    public Week getWeek_id() {
        return week_id;
    }

    public void setWeek_id(Week week_id) {
        this.week_id = week_id;
    }

    public static double calculateScore(int testdata_id) {
        Connection db = null;
        PreparedStatement ps;
        ArrayList<Double> test_resultList = new ArrayList<>();
        double sum = 0;
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query = "SELECT test_result FROM norges_roforbund.registry_testdataresult WHERE testdata_id = ?";
            ps = db.prepareStatement(query);
            ps.setInt(1, testdata_id);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                test_resultList.add(rs.getDouble("test_result"));
            }
            rs.close();

            for (Double test_result : test_resultList)
                sum += test_result;

            return sum / test_resultList.size();

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
        return 0;
    }

}
