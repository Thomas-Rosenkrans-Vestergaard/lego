<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" value="Profile" scope="request"/>
<%@ include file="include/top.jspf" %>
<div class="row">
    <div class="col s12">
        <h2>Administration</h2>
    </div>
</div>
<div class="row">
    <div class="col s12">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Member email</th>
                <th>Width</th>
                <th>Height</th>
                <th>Depth</th>
                <th>Specification</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td><c:out value="${order.getId()}"/></td>
                    <td><c:out value="${order.getWidth()}"/></td>
                    <td><c:out value="${order.getHeight()}"/></td>
                    <td><c:out value="${order.getDepth()}"/></td>
                    <td><c:out value="${order.getSpecification()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="include/bot.jspf" %>