<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<title>Login</title>

<%
    if (session.getAttribute("email") == null) {
        out.println("<div class=\"content-section\">\n" +
                "    <form action=\"Login\" method=\"post\">\n" +
                "        <fieldset class=\"form-group\">\n" +
                "            <legend class=\"border-bottom mb-4\">Login</legend>\n" +
                "\n" +
                "            <div id=\"div_id_username\" class=\"form-group\">\n" +
                "                <label for=\"id_email\" class=\" requiredField\">\n" +
                "                    Email\n" +
                "                    <span class=\"asteriskField\">*</span>\n" +
                "                </label>\n" +
                "                <input type=\"email\" name=\"email\" autofocus autocapitalize=\"none\"\n" +
                "                       pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2, 4}$\" autocomplete=\"email\"\n" +
                "                       maxlength=\"254\" class=\"emailinput form-control\" required id=\"id_email\">\n" +
                "            </div>\n" +
                "\n" +
                "            <div id=\"div_id_password\" class=\"form-group\">\n" +
                "                <label for=\"id_password\" class=\" requiredField\">\n" +
                "                    Password\n" +
                "                    <span class=\"asteriskField\">*</span>\n" +
                "                </label>\n" +
                "                <input type=\"password\" name=\"password\" pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}\"\n" +
                "                       autocomplete=\"current-password\"\n" +
                "                       class=\"textinput textInput form-control\" required id=\"id_password\">\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"text-center\">\n" +
                "                <button class=\"button-primary\" type=\"submit\">Login</button>\n" +
                "                <small class=\"ml-2\"><a href=\"#\">Forgot your password?</a></small>\n" +
                "            </div>\n" +
                "\n" +
                "        </fieldset>\n" +
                "    </form>\n" +
                "</div>\n");
    }
%>

<jsp:include page="/footer.jsp"/>
