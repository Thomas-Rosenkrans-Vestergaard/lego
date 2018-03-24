/**
 * Renders the provided wall data on the provided canvas.
 * @param canvas The canvas to render the wall on.
 * @param wall The wall data to render.
 * @param width The width of the wall.
 * @param height The height of the wall.
 */
function renderWall(canvas, wall, width, height) {
    var ctx = canvas.getContext("2d");
    ctx.translate(0.5, 0.5);
    var unitWidth = Math.floor(canvas.width / width);
    canvas.height = canvas.width * (height * 2 / width);
    var unitHeight = unitWidth * 2;
    wall['bricks'].forEach(function (brick) {
        ctx.fillStyle = getRandomColor();
        ctx.fillRect(brick.x * unitWidth, canvas.height - (brick.y * unitHeight + unitHeight), unitWidth * brick.length, unitHeight);
    });
}

/**
 * Returns a random hex color code.
 * @returns {string} The random hex color code.
 */
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}