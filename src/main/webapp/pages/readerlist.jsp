<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.readers" bundle="${var}"/></title>
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
                        <th onclick="sortTable(1)" scope="col"><fmt:message key="readers.id" bundle="${var}"/></th>
                        <th onclick="sortTable(2)" scope="col"><fmt:message key="readers.email" bundle="${var}"/></th>
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
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="${pageContext.request.contextPath}/controller?command=user_page&userId=${userListDto.id}"
                                       class="btn btn-dark btn-sm"><fmt:message key="btn.info"
                                                                                bundle="${var}"/></a>
                                </div>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a href="${pageContext.request.contextPath}/controller?command=remove_reader&userId=${userListDto.id}"
                                       class="btn btn-danger btn-sm"><fmt:message key="btn.delete"
                                                                                  bundle="${var}"/></a>
                                </div>
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