<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="java.util.ArrayList" %>
<%@ page import="models.User" %>
<%@page import="models.Class" %>
<%@ page import="models.Week" %>

<title>Testdata Create</title>

<div class="content-section">
    <form action="TestdataCreate" method="POST">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Testdata Create</legend>

            <input type="hidden" name="year" value="" id="id_year">

            <div id="div_id_week" class="form-group">
                <label for="id_week" class=" requiredField">
                    Week
                    <span class="asteriskField">*</span>
                </label>
                <select name="week" class="select form-control" required id="id_week">
                    <option value="" selected>---------</option>
                    <%
                        ArrayList<Week> weekList = (ArrayList<Week>) request.getAttribute("weekList");
                        for (Week week : weekList) {
                            out.println("<option value=\"" + week.getId() + "\">" + week.getNumber() + "</option>");
                        }
                    %>
                </select>
            </div>

            <div id="div_id_class" class="form-group">
                <label for="id_class" class=" requiredField">
                    Class
                    <span class="asteriskField">*</span>
                </label>
                <select name="class" class="select form-control" required id="id_class">
                    <option value="" selected>---------</option>
                    <%
                        ArrayList<Class> classList = (ArrayList<Class>) request.getAttribute("classList");
                        for (Class class_ : classList) {
                            out.println("<option value=\"" + class_.getId() + "\">" + class_.getName() + "</option>");
                        }
                    %>
                </select>
            </div>

            <div id="div_id_utøver" class="form-group">
                <label for="id_utøver" class=" requiredField">
                    Utøver
                    <span class="asteriskField">*</span>
                </label>
                <select name="utøver" class="select form-control" required id="id_utøver">
                    <option value="" selected>---------</option>
                    <%
                        ArrayList<User> userList = (ArrayList<User>) request.getAttribute("userList");
                        for (User utøver : userList) {
                            out.println("<option value=\"" + utøver.getId() + "\">" + utøver.getFirst_name() + " " + utøver.getLast_name() + "</option>");
                        }
                    %>
                </select>
            </div>

            <div class="text-center">
                <button class="button-primary" type="submit">Create</button>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
