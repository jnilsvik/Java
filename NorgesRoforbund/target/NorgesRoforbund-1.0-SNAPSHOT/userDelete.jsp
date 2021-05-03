<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="models.User" %>

<title>User Delete</title>

<div class="content-section">
    <form action="UserDelete" method="POST">
        <%
            User user = (User) request.getAttribute("user");
            out.println("<input hidden type=\"text\" name=\"id\" value=\"" + user.getId() + "\">");
        %>
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">User Delete</legend>
            <h2>Are you sure you want to delete this account?</h2>
        </fieldset>
        <div class="form-group">
            <button class="button-secondary" type="submit">Yes, Delete</button>
            <small class="ml-2">
                <a href="#" onclick="history.go(-1)">
                    <button class="button-primary" type="button">No, Cancel</button>
                </a>
            </small>
        </div>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
