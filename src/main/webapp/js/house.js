$(document).ready(function () {

    var door_switch = $('#door-switch');
    var door_inputs = $('.door-input');
    var window_switch = $('#window-switch');
    var window_inputs = $('.window-input');

    door_switch.on('change', function () {
        if (door_switch.is(':checked')) {
            door_inputs.removeAttr("disabled");
            $('#door-row').show(500);
            return;
        }

        door_inputs.attr("disabled", "disabled");
        $('#door-row').hide(500);
    });

    window_switch.on('change', function () {
        if (window_switch.is(':checked')) {
            window_inputs.removeAttr("disabled");
            $('#window-row').show(500);
            return;
        }

        window_inputs.attr("disabled", "disabled");
        $('#window-row').hide(500);
    });
});