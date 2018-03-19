<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Member" scope="request"/>
<%@ include file="include/top.jspf" %>
<h2>Member</h2>
<div class="row">
    <div class="col s6">
        <h3>Create user account</h3>
        <form action="member" method="POST">
            <input type="hidden" name="action" value="register">
            <div class="input-field col s12">
                <input type="email" id="register-email-input" class="validate" name="email" required>
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
                <input class="button-submit btn-large" type="submit" value="Create account.">
            </div>
        </form>
    </div>
    <div class="col s6">
        <h3>Login to user account</h3>
        <form action="member" method="POST">
            <input type="hidden" name="action" value="login">
            <div class="input-field col s12">
                <input type="email" id="login-email-input" class="validate" name="email" required>
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
<%@ include file="include/bot.jspf" %>