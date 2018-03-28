<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Home" scope="request"/>
<%@ include file="include/top.jspf" %>
<script src="js/render.js"></script>
<script src="js/home.js"></script>
<div class="row">
    <div class="col s12">
        <h2>Home</h2>
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
<form class="row section" id="house-form" method="POST">
    <div class="row">
        <div class="col s12">
            <h4>Dimensions</h4>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <p>The dimensions of the lego house. The width and depth are measured in dots, whereas the height is
                meassured in bricks.</p>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12 l4">
            <input id="width" type="number" class="validate" name="width" value="22" min="8" required>
            <label for="width">Width</label>
        </div>
        <div class="input-field col s12 l4">
            <input id="height" type="number" class="validate" name="height" value="4" min="4" required>
            <label for="height">Height</label>
        </div>
        <div class="input-field col s12 l4">
            <input id="depth" type="number" class="validate" name="depth" value="22" min="8" required>
            <label for="depth">Depth</label>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <h4>Door</h4>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <div class="switch">
                <label>
                    Off
                    <input id="door-switch" type="checkbox" name="door" checked="checked">
                    <span class="lever"></span>
                    On
                </label>
            </div>
        </div>
    </div>
    <div class="row" id="door-row">
        <div class="row">
            <div class="col s12">
                <p>Chose the side where the door is to be placed.</p>
            </div>
        </div>
        <div class="row">
            <div class="col s12 l6">
                <p>
                    <input class="door-input" name="door-side" type="radio" value="1" id="door-front" checked="checked"
                           required/>
                    <label for="door-front">Front</label>
                </p>
                <p>
                    <input class="door-input" name="door-side" type="radio" value="2" id="door-left" required/>
                    <label for="door-left">Left</label>
                </p>
                <p>
                    <input class="door-input" name="door-side" type="radio" value="3" id="door-right" required/>
                    <label for="door-right">Right</label>
                </p>
                <p>
                    <input class="door-input" name="door-side" type="radio" value="4" id="door-back" required/>
                    <label for="door-back">Back</label>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <p>Chose the position of the door on the chosen side.</p>
            </div>
        </div>
        <div class="input-field col s12">
            <input id="door-x" type="number" class="validate door-input" name="door-x" min="2" value="2" required>
            <label for="door-x">The x position of the door (lower left corner)</label>
        </div>
        <div class="row">
            <div class="col s12">
                <p>Chose the dimensions of the door on the chosen side.</p>
            </div>
        </div>
        <div class="input-field col s12 l6">
            <input id="door-width" type="number" class="validate door-input" name="door-width" min="1" value="4"
                   required>
            <label for="door-width">The width of the door (in dots)</label>
        </div>
        <div class="input-field col s12 l6">
            <input id="door-height" type="number" class="validate door-input" name="door-height" min="1" value="3"
                   required>
            <label for="door-height">The height of the door (in bricks)</label>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <h4>Window</h4>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <div class="switch">
                <label>
                    Off
                    <input id="window-switch" type="checkbox" name="window" checked="checked">
                    <span class="lever"></span>
                    On
                </label>
            </div>
        </div>
    </div>
    <div class="row" id="window-row">
        <div class="row">
            <div class="col s12">
                <p>Chose the side where the window is to be placed.</p>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <p>
                    <input class="window-input" name="window-side" type="radio" value="1" id="window-front"
                           checked="checked" required/>
                    <label for="window-front">Front</label>
                </p>
                <p>
                    <input class="window-input" name="window-side" type="radio" value="2" id="window-left" required/>
                    <label for="window-left">Left</label>
                </p>
                <p>
                    <input class="window-input" name="window-side" type="radio" value="3" id="window-right" required/>
                    <label for="window-right">Right</label>
                </p>
                <p>
                    <input class="window-input" name="window-side" type="radio" value="4" id="window-back" required/>
                    <label for="window-back">Back</label>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <p>Chose the position of the window on the chosen side.</p>
            </div>
        </div>
        <div class="input-field col s12 l6">
            <input id="window-x" type="number" class="validate window-input" name="window-x" min="2" value="8" required>
            <label for="window-x">The x position of the window (lower left corner)</label>
        </div>
        <div class="input-field col s12 l6">
            <input id="window-y" type="number" class="validate window-input" name="window-y" value="1" min="1" required>
            <label for="window-y">The y position of the window (lower left corner)</label>
        </div>
        <div class="row">
            <div class="col s12">
                <p>Chose the dimensions of the window on the chosen side.</p>
            </div>
        </div>
        <div class="input-field col s12 l6">
            <input id="window-width" type="number" class="validate window-input" name="window-width" min="1" value="4"
                   required>
            <label for="window-width">The width of the window (in dots)</label>
        </div>
        <div class="input-field col s12 l6">
            <input id="window-height" type="number" class="validate window-input" name="window-height" min="1" value="2"
                   required>
            <label for="window-height">The height of the window (in bricks)</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <button class="btn-large waves-effect waves-light" id="preview-button">
                PREVIEW <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <button class="btn-large waves-effect waves-light" type="submit" id="order-button">
                ORDER <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
</form>
<div id="preview-row">
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
<%@ include file="include/bot.jspf" %>