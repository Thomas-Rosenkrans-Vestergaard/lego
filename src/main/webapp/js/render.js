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

function populateTable(table, data) {
    var onesRow = table.find('.ones-row');
    var twosRow = table.find('.twos-row');
    var foursRow = table.find('.fours-row');

    populateRow(onesRow, data['front']['ones'], data['back']['ones'], data['left']['ones'], data['right']['ones'], data['ones']);
    populateRow(twosRow, data['front']['twos'], data['back']['twos'], data['left']['twos'], data['right']['twos'], data['twos']);
    populateRow(foursRow, data['front']['fours'], data['back']['fours'], data['left']['fours'], data['right']['fours'], data['fours']);
}

function populateRow(row, front, back, left, right, total) {
    var tds = row.find('td');

    tds[1].innerText = front;
    tds[2].innerText = back;
    tds[3].innerText = left;
    tds[4].innerText = right;
    tds[5].innerText = total;
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