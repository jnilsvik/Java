<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="java.util.ArrayList" %>
<%@page import="models.User" %>
<%@ page import="static utils.DbQueries.getSuperuserEmail" %>
<%@ page import="static utils.SessionRetrieval.getSessionUser" %>

<title>User List</title>

<div class="row">
    <div class="col-md">
        <div class="card card-body">
            <% User sessionUser = getSessionUser(request);
                int sessionRole_id = sessionUser.getRole_id().getId();
                if (sessionRole_id != 3) {
                    out.println("            <div class=\"text-center\">\n" +
                            "                <small class=\"ml-2\">\n" +
                            "                    <a href=\"UserCreate\">\n" +
                            "                        Want To Add A New User?\n" +
                            "                    </a>\n" +
                            "                </small>\n" +
                            "            </div>\n");
                }
            %>
            <table class="table table-sm mb-0">
                <tr>
                    <th>Image</th>
                    <th>Full Name</th>
                    <th>Email</th>

                    <% if (String.valueOf(session.getAttribute("email")).equals(getSuperuserEmail())) {
                        out.println("<th>Club</th>\n");
                    } else {
                        out.println("<th>Trener</th>\n");
                    }
                    %>

                </tr>

                <%
                    ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
                    if (userList != null) {
                        for (User user : userList) {
                            out.println("<tr>\n" +
                                    "<td><img class=\"rounded-circle img-list\" src=\"data:image/jpg;base64," + user.getImage() + "\" onerror=\"this.onerror=null; this.src='media/user.jpg'\"/></td>\n" +
                                    "<td><a href=\"UserDetails?id=" + user.getId() + "\">" + user.getFirst_name() + " " + user.getLast_name() + "</a></td>\n" +
                                    "<td>" + user.getEmail() + "</td>\n");

                            if (String.valueOf(session.getAttribute("email")).equals(getSuperuserEmail())) {
                                out.println("<td>" + user.getClub_id().getName() + "</td>\n");
                            } else {
                                out.println("<td class=\"role\">" + user.getRole_id().getName() + "</td>\n" +
                                        "</tr>\n");
                            }
                        }
                    }
                %>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>
