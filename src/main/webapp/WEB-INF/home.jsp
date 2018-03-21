<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Home" scope="request"/>
<%@ include file="include/top.jspf" %>
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
<form class="row section" action="house" method="GET">
    <div class="row">
        <div class="col s12">
            <h4>Dimensions</h4>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <p>Input the dimensions of your lego house.</p>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12 l4">
            <input id="width" type="number" class="validate" name="width" value="12" min="8" required>
            <label for="width">The width of the house (in dots).</label>
        </div>
        <div class="input-field col s12 l4">
            <input id="height" type="number" class="validate" name="height" value="4" min="4" required>
            <label for="height">The height of the house (in bricks)</label>
        </div>
        <div class="input-field col s12 l4">
            <input id="depth" type="number" class="validate" name="depth" value="8" min="8" required>
            <label for="depth">The depth of the house (in dots)</label>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <h4>Door</h4>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <p>Chose the side where the door is to be placed.</p>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <div class="switch">
                <label>
                    Off
                    <input id="door-switch" type="checkbox" checked="checked">
                    <span class="lever"></span>
                    On
                </label>
            </div>
        </div>
    </div>
    <div class="row" id="door-row">
        <div class="col s12">
            <p>
                <input class="door-input" name="door" type="radio" value="1" id="door-front" required/>
                <label for="door-front">Front</label>
            </p>
            <p>
                <input class="door-input" name="door" type="radio" value="2" id="door-left" required/>
                <label for="door-left">Left</label>
            </p>
            <p>
                <input class="door-input" name="door" type="radio" value="3" id="door-right" required/>
                <label for="door-right">Right</label>
            </p>
            <p>
                <input class="door-input" name="door" type="radio" value="4" id="door-back" required/>
                <label for="door-back">Back</label>
            </p>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <h4>Window</h4>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <p>Chose the side where the window is to be placed.</p>
        </div>
    </div>
    <div class="row">
        <div class="col s12">
            <div class="switch">
                <label>
                    Off
                    <input id="window-switch" type="checkbox" checked="checked">
                    <span class="lever"></span>
                    On
                </label>
            </div>
        </div>
    </div>
    <div class="row" id="window-row">
        <div class="col s12">
            <p>
                <input class="window-input" name="window" type="radio" value="1" id="window-front" required/>
                <label for="window-front">Front</label>
            </p>
            <p>
                <input class="window-input" name="window" type="radio" value="2" id="window-left" required/>
                <label for="window-left">Left</label>
            </p>
            <p>
                <input class="window-input" name="window" type="radio" value="3" id="window-right" required/>
                <label for="window-right">Right</label>
            </p>
            <p>
                <input class="window-input" name="window" type="radio" value="4" id="window-back" required/>
                <label for="window-back">Back</label>
            </p>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <button class="btn-large waves-effect waves-light" type="submit">
                Submit <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
    <div class="row">
        <div class="col s12 preview-canvas">

        </div>
    </div>
</form>
<%@ include file="include/bot.jspf" %>