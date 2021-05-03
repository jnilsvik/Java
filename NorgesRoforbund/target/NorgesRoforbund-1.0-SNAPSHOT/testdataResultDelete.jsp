<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="models.TestdataResult" %>

<title>Testdata Result Delete</title>

<div class="content-section">
    <form action="TestdataResultDelete" method="POST">
        <%
            TestdataResult testdataResult = (TestdataResult) request.getAttribute("testdataResult");
            out.println("<input hidden type=\"text\" name=\"id\" value=\"" + testdataResult.getId() + "\">" +
                    "<input hidden type=\"text\" name=\"testdata_id\" value=\"" + testdataResult.getTestdata_id().getId() + "\">\n");
        %>

        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Testdata Result Delete</legend>
            <h2>Are you sure you want to delete this testdata result?</h2>
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
