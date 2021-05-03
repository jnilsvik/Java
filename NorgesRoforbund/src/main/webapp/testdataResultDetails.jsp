<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@page import="models.User" %>
<%@ page import="models.TestdataResult" %>
<%@ page import="static utils.SessionRetrieval.getSessionUser" %>

<title>Testdata Result Details</title>

<div class="row">
    <div class="col-md">
        <div class="card card-body">
            <table class="table table-sm mb-0">
                <div class="text-center">

                    <% User sessionUser = getSessionUser(request);
                        int sessionRole_id = sessionUser.getRole_id().getId();
                        if (sessionRole_id == 2) {
                            out.println("                    <small class=\"ml-2\">\n" +
                                    "                        <a href=\"TestdataResultCreate?id=" + request.getParameter("id") + "\">\n" +
                                    "                            Want To Add A New testdata result?\n" +
                                    "                        </a>\n" +
                                    "                    </small>\n");
                        }
                    %>

                </div>

                <tr>
                    <th>Test Name</th>
                    <th>Test Result</th>
                    <th>Test Time</th>

                    <%
                        if (sessionRole_id == 2) {
                            out.println("                    <th>Update</th>\n" +
                                    "                    <th>Delete</th>\n");
                        }
                    %>

                </tr>
                <tr>
                        <%
                        ArrayList<TestdataResult> testdataResultList = (ArrayList<TestdataResult>) request.getAttribute("testdataResultList");
                        if (testdataResultList != null) {
                            for (TestdataResult testdataResult : testdataResultList) {
                                out.println("<td>" + testdataResult.getTest_id().getName() + "</td>\n" +
                                        "<td>" + testdataResult.getTest_result() + "</td>\n" +
                                        "<td>" + testdataResult.getTest_time() + "</td>\n");
                                if (sessionRole_id == 2) {
                                    out.println("                    <td>\n" +
                                            "                        <a href=\"TestdataResultUpdate?id=" + testdataResult.getId() + "\">\n" +
                                            "                            <button class=\"button-primary\">Update</button>\n" +
                                            "                        </a>\n" +
                                            "                    </td>\n" +
                                            "                    <td>\n" +
                                            "                        <a href=\"TestdataResultDelete?id="+ testdataResult.getId() + "\">\n" +
                                            "                            <button class=\"button-secondary\">Delete</button>\n" +
                                            "                        </a>\n" +
                                            "                    </td>" +
                                             "</tr>\n");
                                } else {
                                    out.println("</tr>");
                                }
                            }
                        }
                    %>
                    <br>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>
