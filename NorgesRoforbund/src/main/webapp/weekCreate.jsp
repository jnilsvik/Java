<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<title>Week Create</title>

<div class="content-section">
    <form action="WeekCreate" method="POST">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Week Create</legend>

            <div id="div_id_number" class="form-group">
                <label for="id_number" class=" requiredField">
                    Number
                    <span class="asteriskField">*</span>
                </label>
                <input type="text" name="number" maxlength="100" class="textInput form-control" required
                       id="id_number">
            </div>

            <div class="text-center">
                <button class="button-primary" type="submit">Create</button>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
