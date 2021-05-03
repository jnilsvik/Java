package servlets;

import models.User;
import utils.DbTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.DbQueries.*;
import static utils.SessionRetrieval.getSessionUser;
import static utils.Validating.validateTestdata;


@WebServlet(name = "TestdataCreate", urlPatterns = {"/TestdataCreate"})
public class TestdataCreate extends Servlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        User sessionUser = getSessionUser(request);
        int sessionRole_id = sessionUser.getRole_id().getId();
        if (sessionRole_id == 2) {
            request.setAttribute("userList", listAllUsers(request));
            request.setAttribute("classList", listAllClasses());
            request.setAttribute("weekList", listAllWeeks());
            request.getRequestDispatcher("testdataCreate.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        int year = Integer.parseInt(request.getParameter("year"));
        int week_id = Integer.parseInt(request.getParameter("week"));
        int class_id = Integer.parseInt(request.getParameter("class"));
        int utøver_id = Integer.parseInt(request.getParameter("utøver"));

        HttpSession session = request.getSession();
        session.setAttribute("year", year);
        session.setAttribute("week_id", week_id);
        session.setAttribute("class_id", class_id);
        session.setAttribute("utøver_id", utøver_id);

        User sessionUser = getSessionUser(request);
        int sessionClub_id = sessionUser.getClub_id().getId();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_inserted = sdf.format(date);
        String date_updated = sdf.format(date);

        if (!validateTestdata(year, week_id, class_id, utøver_id)) {
            Connection db = null;
            PreparedStatement ps;
            try {
                db = DbTool.getINSTANCE().dbLoggIn();
                String query = "INSERT INTO norges_roforbund.registry_testdata (year, date_inserted, date_updated, class_id, club_id, utøver_id, week_id) values (?,?,?,?,?,?,?)";
                ps = db.prepareStatement(query);
                ps.setInt(1, year);
                ps.setString(2, date_inserted);
                ps.setString(3, date_updated);
                ps.setInt(4, class_id);
                ps.setInt(5, sessionClub_id);
                ps.setInt(6, utøver_id);
                ps.setInt(7, week_id);

                ps.execute();
                response.sendRedirect("TestdataResultCreate");

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
        } else {
            response.sendRedirect("TestdataCreate?errorMessage=" + URLEncoder.encode("Testdata already in use!", "UTF-8"));
        }
    }
}
