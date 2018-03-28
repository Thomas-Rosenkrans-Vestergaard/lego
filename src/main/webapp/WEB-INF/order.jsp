<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Profile" scope="request"/>
<%@ include file="include/top.jspf" %>
<div class="row">
    <div class="col s12">
        <h2>Order</h2>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <a href="${back}">Back</a>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum
            tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas
            semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo. Quisque sit amet est et sapien
            ullamcorper pharetra. Vestibulum erat wisi, condimentum sed, commodo vitae, ornare sit amet, wisi. Aenean
            fermentum, elit eget tincidunt condimentum, eros ipsum rutrum orci, sagittis tempus lacus enim ac dui. Donec
            non enim in turpis pulvinar facilisis. Ut felis. Praesent dapibus, neque id cursus faucibus, tortor neque
            egestas augue, eu vulputate magna eros eu erat. Aliquam erat volutpat. Nam dui mi, tincidunt quis, accumsan
            porttitor, facilisis luctus, metus</p>
    </div>
</div>
<div class="row">
    <ul id="profile-information" class="collection with-header user-information">
        <li class="collection-item row">
            <div class="col s6 no-padding">
                <p>Width</p>
            </div>
            <div class="col s6 no-padding">
                <p><c:out value="${order.getWidth()}"/></p>
            </div>
        </li>
        <li class="collection-item row">
            <div class="col s6 no-padding">
                <p>Height</p>
            </div>
            <div class="col s6 no-padding">
                <p><c:out value="${order.getHeight()}"/></p>
            </div>
        </li>
        <li class="collection-item row">
            <div class="col s6 no-padding">
                <p>Depth</p>
            </div>
            <div class="col s6 no-padding">
                <p><c:out value="${order.getDepth()}"/></p>
            </div>
        </li>
    </ul>
</div>
<div class="row" id="preview-row">
    <div class="col s12">
        <ul class="tabs">
            <li class="tab col s2"><a href="#bricks">Bricks</a></li>
            <li class="tab col s2"><a href="#front">Front</a></li>
            <li class="tab col s2"><a href="#back">Back</a></li>
            <li class="tab col s2"><a href="#left">Left</a></li>
            <li class="tab col s2"><a href="#right">Right</a></li>
        </ul>
    </div>
    <div id="bricks" class="col s12 preview-tab">
        <table id="bricks-table">
            <thead>
            <tr>
                <th>Brick</th>
                <th>Front</th>
                <th>Back</th>
                <th>Left</th>
                <th>Right</th>
                <th>Total</th>
            </tr>
            </thead>
            <tbody>
            <tr class="ones-row">
                <td>1x2</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="twos-row">
                <td>2x2</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="fours-row">
                <td>4x2</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="front" class="col s12 preview-tab">
        <canvas width="10024px" class="house-canvas" id="front-canvas"></canvas>
    </div>
    <div id="back" class="col s12 preview-tab">
        <canvas width="10024px" class="house-canvas" id="back-canvas"></canvas>
    </div>
    <div id="left" class="col s12 preview-tab">
        <canvas width="10024px" class="house-canvas" id="left-canvas"></canvas>
    </div>
    <div id="right" class="col s12 preview-tab">
        <canvas width="10024px" class="house-canvas" id="right-canvas"></canvas>
    </div>
</div>
<script src="js/render.js"></script>
<script>
    $("#preview-row").show(0);
    var data = ${order.getSpecification()};
    renderWall(document.getElementById('front-canvas'), data['front'], data['width'], data['height']);
    renderWall(document.getElementById('back-canvas'), data['back'], data['width'], data['height']);
    renderWall(document.getElementById('left-canvas'), data['left'], data['depth'], data['height']);
    renderWall(document.getElementById('right-canvas'), data['right'], data['depth'], data['height']);
    populateTable($("#bricks-table"), data);
</script>
<%@ include file="include/bot.jspf" %>