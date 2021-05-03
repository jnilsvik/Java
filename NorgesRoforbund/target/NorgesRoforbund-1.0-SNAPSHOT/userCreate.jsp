<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="java.util.ArrayList" %>
<%@page import="models.Club" %>
<%@ page import="models.Role" %>
<%@ page import="static utils.DbQueries.getSuperuserEmail" %>

<title>User Create</title>

<div class="content-section">
    <form action="UserCreate" method="POST" enctype="multipart/form-data">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">User Create</legend>

            <div class="form-group">
                <label>Image</label>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" id="id_image" name="image" accept="image/*">
                    <label class="custom-file-label" for="id_image">Choose file...</label>
                </div>
            </div>

            <div id="div_id_first_name" class="form-group">
                <label for="id_first_name" class=" requiredField">
                    First name
                    <span class="asteriskField">*</span>
                </label>
                <input type="text" name="first_name" maxlength="100" class="textInput form-control" required
                       id="id_first_name">
            </div>

            <div id="div_id_last_name" class="form-group">
                <label for="id_last_name" class=" requiredField">
                    Last name
                    <span class="asteriskField">*</span>
                </label>
                <input type="text" name="last_name" maxlength="100"
                       class="textInput form-control" required id="id_last_name">
            </div>

            <div id="div_id_email" class="form-group">
                <label for="id_email" class=" requiredField">
                    Email
                    <span class="asteriskField">*</span>
                </label>
                <input type="email" name="email" maxlength="254" autofocus class="emailinput form-control"
                       required id="id_email">
            </div>

            <div id="div_id_year_of_birth" class="form-group">
                <label for="id_year_of_birth" class=" requiredField">
                    Year of birth
                    <span class="asteriskField">*</span>
                </label>
                <select name="year_of_birth" class="select form-control" required id="id_year_of_birth">
                    <option value="" selected>---------</option>
                </select>
            </div>
            <%
                if (String.valueOf(session.getAttribute("email")).equals(getSuperuserEmail())) {
                    out.println("<div id=\"div_id_club\" class=\"form-group\">\n" +
                            "<label for=\"id_club\" class=\"\">Club</label>\n" +
                            "<select name=\"club\" class=\"select form-control\" id=\"id_club\">\n" +
                            "<option value=\"\" selected>---------</option>\n");

                    ArrayList<Club> clubList = (ArrayList<Club>) request.getAttribute("clubList");
                    if (clubList != null) {
                        for (Club club : clubList) {
                            out.println("<option value=\"" + club.getId() + "\">" + club.getName() + "</option>");
                        }
                    }
                    out.println("</select>\n" +
                            "</div>\n");

                    out.println("<div class=\"form-group\">\n" +
                            "<div id=\"div_id_is_trener\" class=\"form-check\">\n" +
                            "<input type=\"radio\" name=\"role\" value=\"2\" class=\"checkboxinput form-check-input\" required\n" +
                            "id=\"id_is_trener\" checked>\n" +
                            "<label for=\"id_is_trener\" class=\"form-check-label requiredField\">\n" +
                            "Is trener\n" +
                            "<span class=\"asteriskField\">*</span>\n" +
                            "</label>\n" +
                            "</div>\n" +
                            "</div>\n");

                } else {
                    out.println("<div id=\"div_id_role\" class=\"form-group\">\n" +
                            "<label for=\"id_role\" class=\"\">Role</label>\n" +
                            "<select name=\"role\" class=\"select form-control\" id=\"id_role\">\n" +
                            "<option value=\"\" selected>---------</option>\n");

                    ArrayList<Role> roleList = (ArrayList<Role>) request.getAttribute("roleList");
                    if (roleList != null) {
                        for (Role role : roleList) {
                            out.println("<option value=\"" + role.getId() + "\">" + role.getName() + "</option>");
                        }
                    }
                    out.println("</select>\n" +
                            "</div>\n");
                }
            %>

            <div id="div_id_password1" class="form-group">
                <label for="id_password1" class=" requiredField">
                    Password
                    <span class="asteriskField">*</span>
                </label>
                <input type="password" name="password1" autocomplete="new-password"
                       class="textInput form-control" required id="id_password1"
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                <small id="hint_id_password1" class="form-text text-muted">
                    <ul>
                        <li>Your password can’t be too similar to your other personal information.</li>
                        <li>Your password must contain at least 8 characters.</li>
                        <li>Your password can’t be a commonly used password.</li>
                        <li>Your password can’t be entirely numeric.</li>
                    </ul>
                </small>
            </div>

            <div id="div_id_password2" class="form-group">
                <label for="id_password2" class=" requiredField">
                    Password confirmation
                    <span class="asteriskField">*</span>
                </label>
                <input type="password" name="password2" autocomplete="new-password"
                       class="textInput form-control" required id="id_password2"
                       pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                <small id="hint_id_password2" class="form-text text-muted">Enter the same password as before, for
                    verification.</small>
            </div>


            <div class="text-center">
                <button class="button-primary" type="submit">Create</button>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
