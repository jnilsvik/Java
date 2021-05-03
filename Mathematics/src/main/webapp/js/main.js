$(document).ready(function () {
    $('input[type=text]').each(function () {
        let value = $(this).val();
        if (value === 'null') {
            $(this).val(0)
        }
    });
});