<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.books" bundle="${var}"/></title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body>
<div class="container content bg">
    <c:choose>
        <c:when test="${sessionScope.role eq 'librarian'}">
            <div class="d-grid row mx-auto">
                <div class="col-8 mt-3 center-block">
                    <a class="btn btn-info btn-lg btn-block"
                       href="${pageContext.request.contextPath}/controller?command=go_to_add_author_page">
                        <fmt:message key="btn.add_author" bundle="${var}"/>
                    </a>
                </div>
            </div>
        </c:when>
    </c:choose>
    <br/>
    <div class="row">
        <div class="container">
            <table id="sortTable" class="table table-hover" style="background: lightgrey;text-align: center">
                <thead style="background: dimgrey;color:floralwhite">
                <tr>
                    <th onclick="sortTable(0)" scope="col"><fmt:message key="entity.id"
                                                                        bundle="${var}"/></th>
                    <th onclick="sortTable(1)" scope="col"><fmt:message key="book.title" bundle="${var}"/></th>
                    <th onclick="sortTable(2)" scope="col"><fmt:message key="book.author" bundle="${var}"/></th>
                    <th onclick="sortTable(3)" scope="col"><fmt:message key="book.genre" bundle="${var}"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bookListDto" items="${bookListDto}">
                    <tr>
                        <td>
                            <c:out value="${bookListDto.id}"/>
                        </td>
                        <td>
                            <c:out value="${bookListDto.title}"/>
                        </td>
                        <td>
                            <c:forEach var="authorDto" items="${bookListDto.authors}">
                                <c:out value="${authorDto.firstName} ${authorDto.lastName}"/>
                                <br/>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="genreDto" items="${bookListDto.genres}">
                                <c:out value="${genreDto.genreName}"/>
                                <br/>
                            </c:forEach>
                        </td>
                        <td>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="${pageContext.request.contextPath}/controller?command=book_page&bookId=${bookListDto.id}"
                                   class="btn btn-dark btn-sm"><fmt:message key="btn.info"
                                                                            bundle="${var}"/></a>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.role eq 'librarian'}">
                                    <div class="mt-3 d-flex justify-content-between">
                                        <a href="${pageContext.request.contextPath}/controller?command=remove_book&bookId=${bookListDto.id}"
                                           class="btn btn-danger btn-sm"><fmt:message key="btn.delete"
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
<script src="${pageContext.request.contextPath}/js/tablePagination.js"></script>
<script src="${pageContext.request.contextPath}/js/tableSorting.js"></script>
</body>
</html>