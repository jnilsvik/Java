<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@page import="java.util.ArrayList" %>
<%@ page import="models.Test" %>

<title>Testdata Result Create</title>

<div class="content-section">
    <form action="TestdataResultCreate" method="POST">
        <%
            out.println("<input hidden type=\"text\" name=\"id\" value=\"" + request.getParameter("id") + "\">");
        %>

        <input type="text" hidden value="">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Testdata Result Create</legend>

            <div id="div_id_test" class="form-group">
                <label for="id_test" class=" requiredField">
                    Test
                    <span class="asteriskField">*</span>
                </label>
                <select name="test_id" class="select form-control" required id="id_test">
                    <option value="" selected>---------</option>
                    <%
                        ArrayList<Test> testList = (ArrayList<Test>) request.getAttribute("testList");
                        for (Test test : testList) {
                            out.println("<option value=\"" + test.getId() + "\">" + test.getName() + "</option>");
                        }
                    %>
                </select>
            </div>

            <div id="div_id_test_result" class="form-group">
                <label for="id_test_result">Test result</label>
                <input type="number" name="test_result" step="any" class="numberinput form-control"
                       id="id_test_result">
            </div>

            <div id="div_id_test_time" class="form-group">
                <label for="id_test_time">Test time</label>
                <input type="text" name="test_time" class="timeinput form-control"
                       id="id_test_time">
            </div>

            <div class="text-center">
                <button class="button-primary" type="submit">Create</button>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
<script src="js/testdataResultCreate.js"></script>