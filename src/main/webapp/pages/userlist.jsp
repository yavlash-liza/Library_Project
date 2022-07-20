<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.users" bundle="${var}"/></title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body>
<div class="container content bg">
    <div class="row">
        <div class="container">
            <table id="sortTable" class="table table-hover" style="background: lightgrey;text-align: center">
                <thead style="background: dimgrey;color:floralwhite">
                <tr>
                    <th onclick="sortTable(0)" scope="col"><fmt:message key="users.id" bundle="${var}"/></th>
                    <th onclick="sortTable(1)" scope="col"><fmt:message key="users.email" bundle="${var}"/></th>
                    <th onclick="sortTable(2)" scope="col"><fmt:message key="users.role" bundle="${var}"/></th>
                    <th onclick="sortTable(3)" scope="col"><fmt:message key="users.status" bundle="${var}"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userListDto" items="${userListDto}">
                    <tr>
                        <td>
                            <c:out value="${userListDto.id}"/>
                        </td>
                        <td>
                            <c:out value="${userListDto.email}"/>
                        </td>
                        <td>
                            <c:out value="${userListDto.role.roleName}"/>
                        </td>
                        <td>
                            <c:out value="${userListDto.status}"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${userListDto.status eq 'DELETED'}">
                                    <div class="mt-3 d-flex justify-content-between">
                                        <a href="${pageContext.request.contextPath}/controller?command=activate_user&userId=${userListDto.id}"
                                           class="btn btn-success btn-sm"><fmt:message key="btn.activate"
                                                                                       bundle="${var}"/></a>
                                    </div>
                                </c:when>
                                <c:when test="${userListDto.status eq 'ACTIVE'}">
                                    <div class="mt-3 d-flex justify-content-between">
                                        <a href="${pageContext.request.contextPath}/controller?command=remove_user&userId=${userListDto.id}"
                                           class="btn btn-danger btn-sm"><fmt:message key="btn.delete"
                                                                                      bundle="${var}"/></a>
                                    </div>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${userListDto.role.roleName eq 'reader'}">
                                    <div class="mt-3 d-flex justify-content-between">
                                        <a href="${pageContext.request.contextPath}/controller?command=change_to_librarian&userId=${userListDto.id}"
                                           class="btn btn-info btn-sm"><fmt:message key="btn.make_librarian"
                                                                                    bundle="${var}"/></a>
                                    </div>
                                </c:when>
                                <c:when test="${userListDto.role.roleName eq 'librarian'}">
                                    <div class="mt-3 d-flex justify-content-between">
                                        <a href="${pageContext.request.contextPath}/controller?command=change_to_reader&userId=${userListDto.id}"
                                           class="btn btn-warning btn-sm"><fmt:message key="btn.make_reader"
                                                                                    bundle="${var}"/></a>
                                    </div>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="main/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/tableSorting.js"></script>
<script src="${pageContext.request.contextPath}/js/tablePagination.js"></script>
</body>
</html>