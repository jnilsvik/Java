// Show success msg after logging in/out.
setTimeout(function () {
    $('#message').fadeOut('fast');
}, 3000);


// Put the name of the image as label when uploaded in input type "file"
$('input[type="file"]').change(function (e) {
    const fileName = e.target.files[0].name;
    $("label[for='id_image']").text(fileName);
});


// List the years as options in Select tag
const start = new Date().getFullYear() - 80;
const end = new Date().getFullYear() - 12;
for (let year = start; year <= end; year++) {
    const option = new Option(year.toString());
    $(option).html(year);
    $("#id_year_of_birth").append(option);
}


// Replace the role trener with ✔ and utøver with empty String
$('.role').each(function () {
    const cellText = $(this).html();
    $(this).html(cellText.replace(/utøver/g, '&#10060').replace(/trener/g, '&#10004'));
});


// Submit the form after changing the picture
$('#file-input').change(function () {
    $('#form').submit()
});


// Get current year
const date = new Date();
$('#id_year').attr('value', date.getFullYear());


// Download html table to an Excel file
$("#download_button").click(function () {
    $("#exportable_table").table2excel({
        filename: "Downloaded table.xls"
    });
})
