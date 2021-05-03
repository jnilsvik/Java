<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="java.util.ArrayList" %>
<%@ page import="models.User" %>
<%@page import="models.Testdata" %>
<%@ page import="static utils.DbQueries.getSuperuserEmail" %>
<%@ page import="static utils.SessionRetrieval.getSessionUser" %>
<%@ page import="static models.Testdata.calculateScore" %>

<title>Testdata List</title>

<div class="row">
    <div class="col-md">
        <div class="card card-body">
            <% User sessionUser = getSessionUser(request);
                int sessionRole_id = sessionUser.getRole_id().getId();
                if (sessionRole_id == 2) {
                    out.println("            <div class=\"text-center\">\n" +
                            "                <small class=\"ml-2\">\n" +
                            "                    <a href=\"TestdataCreate\">\n" +
                            "                        Want To Add A New Testdata?\n" +
                            "                    </a>\n" +
                            "                </small>\n" +
                            "            </div>\n");
                }
            %>
            <a id="download_button" href="#">Click here to download the table!</a>
            <br>

            <table id="exportable_table" class="table table-sm mb-0">
                <tr>
                    <th>Year</th>
                    <th>Week</th>

                    <% if (String.valueOf(session.getAttribute("email")).equals(getSuperuserEmail())) {
                        out.println("<th>Club</th>\n");
                    }
                    %>

                    <th>Class</th>

                    <%
                        if (sessionRole_id != 3) {
                            out.println("<th>Utøver</th>\n");
                        }
                    %>

                    <th>Score</th>
                    <th>Date Inserted</th>
                    <th>Date Updated</th>
                </tr>
                <%
                    ArrayList<Testdata> testdataList = (ArrayList<Testdata>) request.getAttribute("testdataList");
                    if (testdataList != null) {
                        for (Testdata testdata : testdataList) {
                            out.println("<tr>\n" +
                                    "<td>" + testdata.getYear() + "</td>\n" +
                                    "<td>" + testdata.getWeek_id().getNumber() + "</td>\n");

                            if (String.valueOf(session.getAttribute("email")).equals(getSuperuserEmail())) {
                                out.println("<td>" + testdata.getClub_id().getName() + "</td>\n");
                            }
                            out.println("<td>" + testdata.getClass_id().getName() + "</td>\n");

                            if (sessionRole_id != 3) {
                                out.println("<td><a href=\"UserDetails?id=" + testdata.getUtøver_id().getId() + "\">" + testdata.getUtøver_id().getFirst_name() + " " + testdata.getUtøver_id().getLast_name() + "</a></td>\n");
                            }
                            out.println("<td><a class=\"color-secondary\" href=\"TestdataResultDetails?id=" + testdata.getId() + "\">" + calculateScore(testdata.getId()) + "</a></td>\n" +
                                    "<td>" + testdata.getDate_inserted() + "</td>" +
                                    "<td>" + testdata.getDate_updated() + "</td>");

                        }
                    }
                %>

                <script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
                <script type="text/javascript">
                    window.onload = function () {
                        const chart = new CanvasJS.Chart("chartContainer", {
                            title: {
                                text: "Graphic display of data",
                            },
                            data: [
                                {
                                    type: "line",
                                    dataPoints: [
                                         <% assert testdataList != null;
                                        for (Testdata testdata : testdataList) {
                                        out.println("{\"label\":"+testdata.getYear()+", \"y\": "+calculateScore(testdata.getId())+"},");
                                        }
%>
                                    ]
                                }
                            ]
                        });
                        chart.render();
                    }
                </script>

            </table>
            <%
                if (sessionRole_id == 3) {
                    out.println("<br>" +
                            "<div id=\"chartContainer\" class=\"img-graph m-auto\"></div>\n");
                }
            %>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>
