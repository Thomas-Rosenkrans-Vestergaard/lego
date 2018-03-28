$(document).ready(function () {

    var door_switch = $('#door-switch');
    var door_inputs = $('.door-input');
    door_switch.on('change', function () {
        if (door_switch.is(':checked')) {
            door_inputs.removeAttr("disabled");
            $('#door-row').show(500);
            return;
        }

        door_inputs.attr("disabled", "disabled");
        $('#door-row').hide(500);
    });

    var window_switch = $('#window-switch');
    var window_inputs = $('.window-input');
    window_switch.on('change', function () {
        if (window_switch.is(':checked')) {
            window_inputs.removeAttr("disabled");
            $('#window-row').show(500);
            return;
        }

        window_inputs.attr("disabled", "disabled");
        $('#window-row').hide(500);
    });

    $('#preview-button').click(function (e) {
        $.ajax({
            type: 'GET',
            url: 'generate',
            data: $('#house-form').serialize(),
            success: [function (data) {
                renderWall(document.getElementById('front-canvas'), data['front'], data['width'], data['height']);
                renderWall(document.getElementById('back-canvas'), data['back'], data['width'], data['height']);
                renderWall(document.getElementById('left-canvas'), data['left'], data['depth'], data['height']);
                renderWall(document.getElementById('right-canvas'), data['right'], data['depth'], data['height']);
                populateTable($("#bricks-table"), data);
                $("#preview-row").show(500);
            }]
        });

        e.preventDefault();
    });
});