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
        <div class="input-field col s12 l4">
            <input id="width" type="number" class="validate" name="width" min="5" required>
            <label for="width">Width</label>
        </div>
        <div class="input-field col s12 l4">
            <input id="height" type="number" class="validate" name="height" min="5" required>
            <label for="height">Height</label>
        </div>
        <div class="input-field col s12 l4">
            <input id="depth" type="number" class="validate" name="depth" min="5" required>
            <label for="depth">Depth</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <button class="btn-large waves-effect waves-light" type="submit" name="action">
                Submit <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
</form>
<%@ include file="include/bot.jspf" %>