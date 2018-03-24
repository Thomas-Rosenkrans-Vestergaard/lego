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
                <th>Status</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${not empty orders}">
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td><c:out value="${order.getId()}"/></td>
                            <td><c:out value="${order.getMember().getEmail()}"/></td>
                            <td><c:out value="${order.getWidth()}"/></td>
                            <td><c:out value="${order.getHeight()}"/></td>
                            <td><c:out value="${order.getDepth()}"/></td>
                            <td><c:out value="${order.getStatus()}"/></td>
                            <td>
                                <form method="post">
                                    <input type="hidden" name="id" value="${order.getId()}">
                                    <input class="button-submit btn" type="submit"
                                           value="MARK SENT" ${order.getShippedAt() != null ? 'disabled' : ''}>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">There are no placed no orders.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="include/bot.jspf" %>