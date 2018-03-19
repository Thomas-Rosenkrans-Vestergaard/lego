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
    <div class="col s12">
        <ul id="profile-information" class="collection with-header user-information">
            <li class="collection-item row">
                <div class="col s6 no-padding">
                    <p>Email</p>
                </div>
                <div class="col s6 no-padding">
                    <p><c:out value="${member.getEmail()}"/></p>
                </div>
            </li>
            <li class="collection-item row">
                <div class="col s6 no-padding">
                    <p>Role</p>
                </div>
                <div class="col s6 no-padding">
                    <p><c:out value="${member.getRole()}"/></p>
                </div>
            </li>
            <li class="collection-item row">
                <div class="col s6 no-padding">
                    <p>Created at</p>
                </div>
                <div class="col s6 no-padding">
                    <p><c:out value="${member.getCreatedAt()}"/></p>
                </div>
            </li>
        </ul>
    </div>
</div>
<%@ include file="include/bot.jspf" %>