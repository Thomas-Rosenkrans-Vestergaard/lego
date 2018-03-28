<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Member" scope="request"/>
<%@ include file="include/top.jspf" %>
<div class="row">
    <div class="col s12">
        <h2>Membership</h2>
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
<div class="row section">
    <div class="row col s12 no-padding">
        <div class="row">
            <div class="col s12">
                <h4>Access user account.</h4>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                    Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit
                    amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
            </div>
        </div>
        <form class="row" method="POST">
            <input type="hidden" name="action" value="login">
            <div class="input-field col s12">
                <input type="email" id="login-email-input" class="validate" name="email" data-length="255" required>
                <label for="login-email-input">Email</label>
            </div>
            <div class="input-field col s12">
                <input type="password" id="login-password-input" name="password" class="validate" required>
                <label for="login-password-input">Password</label>
            </div>
            <div class="input-field col s12">
                <input class="button-submit btn-large" type="submit" value="Login">
            </div>
        </form>
    </div>
</div>
<div class="row section">
    <div class="col s12 no-padding">
        <div class="row">
            <div class="col s12">
                <h4>Create user account.</h4>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.
                    Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit
                    amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
            </div>
        </div>
        <form class="row" method="POST">
            <input type="hidden" name="action" value="register">
            <div class="input-field col s12">
                <input type="email" id="register-email-input" class="validate" name="email" data-length="255" required>
                <label for="register-email-input">Email</label>
            </div>
            <div class="input-field col s12">
                <input type="password" id="register-password-input" name="password" class="validate" required>
                <label for="register-password-input">Password</label>
            </div>
            <div class="input-field col s12">
                <input type="password" id="register-password-repeat-input" name="repeat-password" class="validate"
                       required>
                <label for="register-password-repeat-input">Repeat password</label>
            </div>
            <div class="input-field col s12">
                <input class="button-submit btn-large" type="submit" value="Create account">
            </div>
        </form>
    </div>
</div>
<%@ include file="include/bot.jspf" %>