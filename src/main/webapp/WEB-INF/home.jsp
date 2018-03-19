<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Home" scope="request"/>
<%@ include file="include/top.jspf" %>
<h1>Home</h1>
<form action="house" method="GET">
    <div class="row">
        <div class="input-field col s4">
            <input id="width" type="number" class="validate" name="width" min="5" required>
            <label for="width">Width</label>
        </div>
        <div class="input-field col s4">
            <input id="height" type="number" class="validate" name="height" min="5" required>
            <label for="height">Height</label>
        </div>
        <div class="input-field col s4">
            <input id="depth" type="number" class="validate" name="depth" min="5" required>
            <label for="depth">Depth</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s4">
            <button class="btn waves-effect waves-light" type="submit" name="action">
                Submit <i class="material-icons right">send</i>
            </button>
        </div>
    </div>
</form>
<%@ include file="include/bot.jspf" %>