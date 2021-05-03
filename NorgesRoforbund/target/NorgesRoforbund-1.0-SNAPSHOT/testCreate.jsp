<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<title>Test Create</title>

<div class="content-section">
    <form action="TestCreate" method="POST">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Test Create</legend>

            <div id="div_id_name" class="form-group">
                <label for="id_name" class=" requiredField">
                    Name
                    <span class="asteriskField">*</span>
                </label>
                <input type="text" name="name" maxlength="100" class="textInput form-control" required
                       id="id_name">
            </div>

            <div class="form-group">
                <div id="div_id_has_time" class="form-check">
                    <input type="checkbox" name="has_time" class="checkboxinput form-check-input" id="id_has_time">
                    <label for="id_has_time" class="form-check-label">Has time</label>
                </div>
            </div>

            <div class="text-center">
                <button class="button-primary" type="submit">Create</button>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
