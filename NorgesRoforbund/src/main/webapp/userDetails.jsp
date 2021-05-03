<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="models.User" %>

<title>User Details</title>

<div class="content-section">
    <fieldset class="form-group">
        <legend class="border-bottom mb-4">User Details</legend>
        <div class="text-center">
                <% User user = (User) request.getAttribute("user");
                out.println("<p><img class=\"rounded-circle img-user-details\" src=\"data:image/jpg;base64," + user.getImage() + "\" onerror=\"this.onerror=null; this.src='media/user.jpg'\"/>\n</p>\n" +
"        </div>\n" +
"        <label>Full Name: </label>\n" +
"        <p>"+ user.getFirst_name() + " " + user.getLast_name() + "</p>\n" +
"        <label>Email: </label>\n" +
"        <p>"+ user.getEmail() +"</p>\n" +
"        <label>Year Of Birth: </label>\n" +
"        <p>"+ user.getYear_of_birth() +"</p>\n" +
"        <label>Trener: </label>\n" +
"        <p class=\"role\">"+ user.getRole_id().getName() +"</p>\n" +
"\n");
                User sessionUser = (User) session.getAttribute("user");
                if(sessionUser.getRole_id().getId() == 2) {
                out.println(
"        <div class=\"text-center\">\n" +
"            <a href=\"UserUpdate?id="+ user.getId() +"\">\n" +
"                <button class=\"button-primary\">Update</button>\n" +
"            </a>\n" +
"            <a href=\"UserDelete?id=" + user.getId() + "\">\n" +
"                <button class=\"button-secondary\">Delete</button>\n" +
"            </a>\n" +
"        </div>\n");
}
                %>
    </fieldset>
</div>

<jsp:include page="/footer.jsp"/>
