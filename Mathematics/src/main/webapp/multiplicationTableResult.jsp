<%@page import="Classes.MultiplicationTable" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <%@include file="header.jsp" %>
    <title>Result</title>

</head>

<body>
<%
    int quantity = (int) request.getAttribute("quantity");
    int upperLimit = (int) request.getAttribute("upperLimit");

    MultiplicationTable mT = new MultiplicationTable();
    mT.makeSeveralMultiplicationTables(out, quantity, upperLimit);

%>

<%@include file="footer.jsp" %>

</body>

</html>
