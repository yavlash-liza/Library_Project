<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.reader" bundle="${var}"/></title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body>
<div class="content bg">
    <div class="container lign-self-end">
        <div class="main-body">
            <div class="row gutters-sm">
                <div class="col-md-4">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img class="item" src="${pageContext.request.contextPath}/images/avatar.png"
                             alt="Card image cap" height="250" width="250">
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="users.role" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.role.roleName}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="reader.firstname" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.firstName}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="reader.lastname" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.lastName}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="reader.birthdate" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.birthDate}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="reader.address" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.address}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="reader.email" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.email}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="d-grid row mx-auto">
            <div class="col-8 mt-3 center-block">
                <a class="btn btn-info btn-lg btn-block"
                   href="${pageContext.request.contextPath}/controller?command=update_user&userId=${user.id}">
                    <fmt:message key="btn.change_info" bundle="${var}"/>
                </a>
            </div>
        </div>
    </div>
    <br/>
    <c:choose>
        <c:when test="${user.role.roleName eq 'reader'}">
            <div class="container lign-self-end" bg>
                <h2><fmt:message key="reader.orders" bundle="${var}"/></h2>
                <table id="sortTable" class="table table-hover" style="background: lightgrey;text-align: center">
                    <thead style="background: dimgrey;color:floralwhite">
                    <tr>
                        <th onclick="sortTable(0)" scope="col"><fmt:message key="orders.id" bundle="${var}"/></th>
                        <th onclick="sortTable(1)" scope="col"><fmt:message key="orders.creationDate"
                                                                            bundle="${var}"/></th>
                        <th onclick="sortTable(2)" scope="col"><fmt:message key="orders.expirationdate"
                                                                            bundle="${var}"/></th>
                        <th onclick="sortTable(3)" scope="col"><fmt:message key="orders.finalsum" bundle="${var}"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="orderListDto" items="${user.orders}">
                        <tr>
                            <td>
                                <c:out value="${orderListDto.id}"/>
                            </td>
                            <td>
                                <c:out value="${orderListDto.creationDate}"/>
                            </td>
                            <td>
                                <c:out value="${orderListDto.expirationDate}"/>
                            </td>
                            <td>
                                <c:out value="${orderListDto.finalSum}"/>
                            </td>
                            <td>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="${pageContext.request.contextPath}/controller?command=order_page&orderId=${orderListDto.id}"
                                       class="btn btn-dark btn-sm"><fmt:message key="orders.order.info"
                                                                                bundle="${var}"/></a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
    </c:choose>
</div>
</body>
<%@include file="main/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/tableSorting.js"></script>
<script src="${pageContext.request.contextPath}/js/tablePagination.js"></script>
</html>