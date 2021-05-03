package servlets;

import models.Class;
import models.*;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.SessionRetrieval.getSessionUser;


@WebServlet(name = "TestdataList", urlPatterns = {"/TestdataList"})
public class TestdataList extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");

        Connection db = null;
        PreparedStatement ps;
        ArrayList<Testdata> testdataList = new ArrayList<>();
        try {
            db = DbTool.getINSTANCE().dbLoggIn();
            String query;
            User sessionUser = getSessionUser(request);
            int sessionRole_id = sessionUser.getRole_id().getId();
            if (sessionRole_id == 1) {
                query = "SELECT registry_testdata.id as testdata_id, year, date_inserted, date_updated, registry_class.id as class_id, registry_class.name as class_name, account_club.id as club_id, account_club.name as club_name, account_user.id as user_id, account_user.first_name as user_first_name,account_user.last_name as user_last_name, registry_week.id as week_id, registry_week.number as week_number FROM norges_roforbund.registry_testdata INNER JOIN norges_roforbund.account_club ON registry_testdata.club_id = account_club.id INNER JOIN norges_roforbund.registry_class ON registry_testdata.class_id = registry_class.id INNER JOIN norges_roforbund.registry_week ON registry_testdata.week_id = registry_week.id INNER JOIN norges_roforbund.account_user ON registry_testdata.utøver_id = account_user.id ORDER BY year DESC, week_number DESC, class_id, user_first_name, user_last_name";
            } else if (sessionRole_id == 2) {
                int sessionClub_id = sessionUser.getClub_id().getId();
                query = "SELECT registry_testdata.id as testdata_id, year, date_inserted, date_updated, registry_class.id as class_id, registry_class.name as class_name, account_club.id as club_id, account_club.name as club_name, account_user.id as user_id, account_user.first_name as user_first_name,account_user.last_name as user_last_name, registry_week.id as week_id, registry_week.number as week_number FROM norges_roforbund.registry_testdata INNER JOIN norges_roforbund.account_club ON registry_testdata.club_id = account_club.id INNER JOIN norges_roforbund.registry_class ON registry_testdata.class_id = registry_class.id INNER JOIN norges_roforbund.registry_week ON registry_testdata.week_id = registry_week.id INNER JOIN norges_roforbund.account_user ON registry_testdata.utøver_id = account_user.id WHERE registry_testdata.club_id = " + sessionClub_id + "  ORDER BY year DESC, week_number DESC, class_id, user_first_name, user_last_name";
            } else {
                int sessionUtøver_id = sessionUser.getId();
                query = "SELECT registry_testdata.id as testdata_id, year, date_inserted, date_updated, registry_class.id as class_id, registry_class.name as class_name, account_club.id as club_id, account_club.name as club_name, account_user.id as user_id, account_user.first_name as user_first_name,account_user.last_name as user_last_name, registry_week.id as week_id, registry_week.number as week_number FROM norges_roforbund.registry_testdata INNER JOIN norges_roforbund.account_club ON registry_testdata.club_id = account_club.id INNER JOIN norges_roforbund.registry_class ON registry_testdata.class_id = registry_class.id INNER JOIN norges_roforbund.registry_week ON registry_testdata.week_id = registry_week.id INNER JOIN norges_roforbund.account_user ON registry_testdata.utøver_id = account_user.id WHERE registry_testdata.utøver_id = " + sessionUtøver_id + "  ORDER BY year DESC, week_number DESC, class_id";
            }
            ps = db.prepareStatement(query);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Testdata testdata = new Testdata();

                testdata.setId(rs.getInt("testdata_id"));
                testdata.setYear(rs.getInt("year"));
                testdata.setDate_inserted(rs.getString("date_inserted"));
                testdata.setDate_updated(rs.getString("date_updated"));

                Class class_ = new Class();
                class_.setId(rs.getInt("class_id"));
                class_.setName(rs.getString("class_name"));
                testdata.setClass_id(class_);

                Club club = new Club();
                club.setId(rs.getInt("club_id"));
                club.setName(rs.getString("club_name"));
                testdata.setClub_id(club);

                User utøver = new User();
                utøver.setId(rs.getInt("user_id"));
                utøver.setFirst_name(rs.getString("user_first_name"));
                utøver.setLast_name(rs.getString("user_last_name"));
                testdata.setUtøver_id(utøver);

                Week week = new Week();
                week.setId(rs.getInt("week_id"));
                week.setNumber(rs.getInt("week_number"));
                testdata.setWeek_id(week);

                testdataList.add(testdata);
            }
            request.setAttribute("testdataList", testdataList);
            request.getRequestDispatcher("testdataList.jsp").forward(request, response);
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
