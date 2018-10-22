function readURL(input) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            $('#previewImg').removeAttr('hidden');
            $('#previewImg').attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

$("#cameraButton").click(function () {
    $("#selfieInput").trigger('click');
});

$('#selfieInput').on('change', function() {
    var val = $(this).val();
    $(this).siblings('span').text(val);
    readURL(this);
})