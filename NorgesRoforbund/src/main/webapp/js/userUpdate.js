// Remove duplicated options from Select tag since we want to put all the options and the one selected.
const optionValues = [];
$('.select option').each(function(){
    if($.inArray(this.value, optionValues) >-1){
        $(this).remove()
    }else{
        optionValues.push(this.value);
    }
});
