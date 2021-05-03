<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <%@include file="header.jsp" %>
    <title>Simple Calculator</title>

</head>

<body>

<a class="back-button" href="index.jsp">Back To Home Page</a>

<div class="content-section">
    <form action="SimpleCalculatorServlet" target="_self">
        <fieldset class="form-group">

            <legend>Welcome to my simple calculator</legend>

            <p>Please choose 2 numbers and click on the operation you want to perform:</p>

            <label>1st number:</label>
            <input class="form-control" type="text" name="firstNumber" pattern="[0-9 .]+"
                   value="<%= request.getAttribute("firstNumber") %>" autofocus required>
            <br>

            <label>2nd number:</label>
            <input class="form-control" type="text" name="secondNumber" pattern="[0-9 .]+"
                   value="<%= request.getAttribute("secondNumber") %>" autofocus required>
            <br>

            <div class="submit-buttons-div">
                <input type="submit" name="operator" value="+">
                <input type="submit" name="operator" value="-">
                <input type="submit" name="operator" value="*">
                <input type="submit" name="operator" value="/">
                <input type="submit" name="operator" value="%">
            </div>
            <br>

            <label>Result:</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("result") %>" disabled>

        </fieldset>
    </form>
</div>

<%@include file="footer.jsp" %>

</body>

</html>