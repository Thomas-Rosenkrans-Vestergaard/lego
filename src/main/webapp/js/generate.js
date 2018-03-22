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

    function update(canvas, wall, width, height) {
        var ctx = canvas.getContext("2d");
        context.translate(0.5, 0.5);
        var unitWidth = Math.floor(canvas.width / width);
        canvas.height = canvas.width * (height * 2 / width);
        var unitHeight = unitWidth * 2;
        wall.bricks.forEach(function (brick) {
            ctx.fillStyle = getRandomColor();
            ctx.fillRect(brick.x * unitWidth, canvas.height - (brick.y * unitHeight + unitHeight), unitWidth * brick.length, unitHeight);
        });

    }

    function getRandomColor() {
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    $('#house-form').submit(function (e) {
        $.ajax({
            type: 'GET',
            url: 'generate',
            data: $('#house-form').serialize(),
            success: function (data) {
                console.log(data);
                update(document.getElementById('front-canvas'), data.front, data.dimensions.width, data.dimensions.height);
                update(document.getElementById('back-canvas'), data.back, data.dimensions.width, data.dimensions.height);
                update(document.getElementById('left-canvas'), data.left, data.dimensions.depth, data.dimensions.height);
                update(document.getElementById('right-canvas'), data.right, data.dimensions.depth, data.dimensions.height);
            }
        });

        e.preventDefault();
    });
});