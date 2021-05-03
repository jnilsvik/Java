<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <%@include file="header.jsp" %>
    <title>Multiplication Page</title>

</head>

<body>

<a class="back-button" href="index.jsp">Back To Home Page</a>

<div class="content-section">
    <form action="MultiplicationTableServlet">

        <fieldset class="form-group">

            <legend>Welcome to my simple multiplication page</legend>

            <p>Please choose upper limit and quantity of tables you want multiplication table for:</p>

            <label>Upper limit (standard is 10):</label>
            <input class="form-control" type="text" name="upperLimit" value=10 pattern="[0-9 .]+" autofocus required>
            <br>

            <label>Quantity:</label>
            <input class="form-control" type="text" name="quantity" value="1" pattern="[0-9 .]+" autofocus required>
            <br>

            <div class="text-center">
                <input type="submit" value="Show result">
            </div>

        </fieldset>
    </form>
</div>

<%@include file="footer.jsp" %>

</body>

</html>