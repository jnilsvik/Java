// Get the test_result, test_time fields and hide them first.
let test_result_div = $(".numberinput").parent()
let test_time_div = $(".timeinput").parent()

test_result_div.addClass('hidden')
test_time_div.addClass('hidden')

// Show the right div based on selected "Test" name.
$('select').on('change', function () {
    let selected_text = $(this).find("option:selected").text();
    if (selected_text.includes('tid')) {
        $(this).parent().siblings().addClass('hidden')
        $(this).parent().siblings().eq(2).removeClass('hidden')
        $(this).parent().siblings().eq(3).removeClass('hidden')
        $(this).parent().siblings().eq(2).children().attr('placeholder', 'HH:MM:SS')
    } else if (selected_text.includes('---------')) {
        $(this).parent().siblings().addClass('hidden')
    } else {
        $(this).parent().siblings().addClass('hidden')
        $(this).parent().siblings().eq(1).removeClass('hidden')
        $(this).parent().siblings().eq(3).removeClass('hidden')
    }
});