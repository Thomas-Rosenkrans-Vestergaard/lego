<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Profile" scope="request"/>
<%@ include file="include/top.jspf" %>
<div class="row">
    <div class="col s12">
        <h2>Profile</h2>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <ul class="collection with-header user-information">
            <li class="collection-header">
                <h3>Member information</h3>
            </li>
            <li class="collection-item row">
                <div class="col s6 no-padding">
                    <h4>Email</h4>
                </div>
                <div class="col s6 no-padding">
                    <p><c:out value="${member.getEmail()}"/></p>
                </div>
            </li>
            <li class="collection-item row">
                <div class="col s6 no-padding">
                    <h4>Role</h4>
                </div>
                <div class="col s6 no-padding">
                    <p><c:out value="${member.getRole()}"/></p>
                </div>
            </li>
        </ul>
    </div>
</div>
<%@ include file="include/bot.jspf" %>