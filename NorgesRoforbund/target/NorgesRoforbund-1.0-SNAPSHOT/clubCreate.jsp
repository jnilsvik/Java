<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<title>Club Create</title>

<div class="content-section">
    <form action="ClubCreate" method="POST">
        <fieldset class="form-group">
            <legend class="border-bottom mb-4">Club Create</legend>

            <div id="div_id_name" class="form-group">
                <label for="id_name" class=" requiredField">
                    Name
                    <span class="asteriskField">*</span>
                </label>
                <input type="text" name="name" maxlength="100" class="textInput form-control" required
                       id="id_name">
            </div>

            <div class="text-center">
                <button class="button-primary" type="submit">Create</button>
            </div>

        </fieldset>
    </form>
</div>

<jsp:include page="/footer.jsp"/>
