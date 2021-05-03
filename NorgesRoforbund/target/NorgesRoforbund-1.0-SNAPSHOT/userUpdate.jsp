<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Role" %>
<%@ page import="models.User" %>

<title>User Update</title>

<div class="content-section">
    <form action="UserUpdate" method="POST">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">User Update</legend>
            <%
                User user = (User) request.getAttribute("user");
                out.println("<input hidden type=\"text\" name=\"id\" value=\"" + user.getId() + "\">\n" +
                        "<div id=\"div_id_role\" class=\"form-group\">\n" +
                        "<label for=\"id_role\" class=\"\">Role</label>\n" +
                        "<select name=\"role\" class=\"select form-control\" id=\"id_role\">\n" +
                        "<option value=\"" + user.getRole_id().getId() + "\" selected>" + user.getRole_id().getName() + "</option>\n");

                ArrayList<Role> roleList = (ArrayList<Role>) request.getAttribute("roleList");
                if (roleList != null) {
                    for (Role role : roleList) {
                        out.println("<option value=\"" + role.getId() + "\">" + role.getName() + "</option>");
                    }
                }
                out.println("</select>\n" +
                        "</div>\n");
            %>
            <div class="text-center">
                <button class="button-primary" type="submit">Update</button>
            </div>
        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
<script src="js/userUpdate.js"></script>
