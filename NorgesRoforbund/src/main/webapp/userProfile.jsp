<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="models.User" %>

<title>User Profile</title>

<div class="content-section">
    <fieldset class="form-group">
        <legend class="border-bottom mb-4">User Profile</legend>
        <form action="UserProfileImage" id="form" method="POST" enctype="multipart/form-data">
            <%
                User user = (User) session.getAttribute("user");
                out.println("<div class=\"media\">\n" +
                        "<label class=\"m-auto\" for=\"file-input\">\n" +
                        "<img class=\"rounded-circle img-user-profile\" src=\"data:image/jpg;base64," + user.getImage() + "\"\n" +
                        "onerror=\"this.onerror=null; this.src='media/user.jpg'\"\n" +
                        "title=\"Click on the image to change it!\">\n" +
                        "</label>\n" +
                        "<input hidden id=\"file-input\" type=\"file\" name=\"image\"/>\n" +
                        "</div>\n" +
                        "\n" +
                        "        </form>\n" +
                        "        <form action=\"UserProfile\" method=\"post\">\n" +
                        "            <div id=\"div_id_email\" class=\"form-group\">\n" +
                        "                <label for=\"id_email\" class=\" requiredField\">\n" +
                        "                    Email\n" +
                        "                    <span class=\"asteriskField\">*</span>\n" +
                        "                </label>\n" +
                        "                <input type=\"email\" name=\"email\" maxlength=\"254\" autofocus class=\"emailinput form-control\"\n" +
                        "                       required id=\"id_email\" value=\"" + user.getEmail() + "\">\n" +
                        "</div>\n");
            %>
            <div class="text-center">
                <button class="button-primary" type="submit">Update</button>
            </div>
        </form>
    </fieldset>
</div>
<jsp:include page="/footer.jsp"/>
