<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="models.TestdataResult" %>

<title>Testdata Result Update</title>

<div class="content-section">
    <form action="TestdataResultUpdate" method="POST">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Testdata Result Update</legend>

            <%
                TestdataResult testdataResult = (TestdataResult) request.getAttribute("testdataResult");
                out.println("<input hidden type=\"text\" name=\"id\" value=\"" + testdataResult.getId() + "\">\n" +
                           "     <input hidden type=\"text\" name=\"testdata_id\" value=\"" + testdataResult.getTestdata_id().getId() + "\">\n" +
                        "            <div id=\"div_id_test\" class=\"form-group\">\n" +
                        "                <label for=\"id_test\" class=\" requiredField\">\n" +
                        "                    Test\n" +
                        "                    <span class=\"asteriskField\">*</span>\n" +
                        "                </label>\n" +
                        "                <select name=\"test\" class=\"select form-control\" required disabled id=\"id_test\">\n" +
                        "                    <option value=\"" + testdataResult.getTest_id().getId() + "\" selected>" + testdataResult.getTest_id().getName() + "</option>\n" +
                        "                </select>\n" +
                        "            </div>\n" +

                        "            <div id=\"div_id_test_result\" class=\"form-group\">\n" +
                        "                <label for=\"id_test_result\">Test result</label>\n" +
                        "                <input type=\"number\" name=\"test_result\" step=\"any\" class=\"numberinput form-control\"\n" +
                        "                       id=\"id_test_result\" value=\"" + testdataResult.getTest_result() + "\">\n" +
                        "            </div>\n" +
                        "\n" +
                        "            <div id=\"div_id_test_time\" class=\"form-group\">\n" +
                        "                <label for=\"id_test_time\">Test time</label>\n" +
                        "                <input type=\"text\" name=\"test_time\" class=\"timeinput form-control\"\n" +
                        "                       id=\"id_test_time\" value=\"" + testdataResult.getTest_time() + "\">\n" +
                        "            </div>\n");
            %>

            <div class="text-center">
                <button class="button-primary" type="submit">Update</button>
            </div>
        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
<script src="js/testdataResultUpdate.js"></script>