<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.book" bundle="${var}"/></title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css" type="text/css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body>
<div class="container content bg">
    <br/>
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4">
                <div class="d-flex flex-column align-items-sm-stretch text-center">
                    <img class="item" src="${pageContext.request.contextPath}${bookDto.bookPhotoPath}"
                         alt="Card image cap" height="350" width="350">
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="book.title" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookDto.title}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="book.author" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <c:forEach var="author" items="${bookDto.authors}">
                                    <h6><c:out value="${author.firstName} ${author.lastName}"/></h6>
                                    <br/>
                                </c:forEach>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="book.genre" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <c:forEach var="genre" items="${bookDto.genres}">
                                    <h6><c:out value="${genre.genreName}"/></h6>
                                    <br/>
                                </c:forEach>
                            </div>
                        </div>
                        <hr>

                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="book.releaseyear" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookDto.releaseYear}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="book.pages" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookDto.pages}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </div>
    <c:choose>
        <c:when test="${sessionScope.role eq 'librarian'}">
            <div class="d-grid row mx-auto">
                <div class="col-8 mt-3 center-block">
                    <a class="btn btn-info btn-lg btn-block"
                       href="${pageContext.request.contextPath}/controller?command=update_book_page&bookId=${bookDto.id}">
                        <fmt:message key="btn.change_info" bundle="${var}"/>
                    </a>
                </div>
                <div class="col-4 mt-3 center-block">
                    <a class="btn btn-danger btn-lg btn-block"
                       href="${pageContext.request.contextPath}/controller?command=add_copies_command&bookId=${bookDto.id}">
                        <fmt:message key="btn.add_copies" bundle="${var}"/>
                    </a>
                </div>
            </div>
        </c:when>
    </c:choose>
    <br>
    <div class="container lign-self-end" bg>
        <h2><fmt:message key="book.bookCopies.title" bundle="${var}"/></h2>
        <table id="sortTable" class="table table-hover" style="background: lightgrey;text-align: center">
            <thead style="background: dimgrey;color:floralwhite">
            <tr>
                <th onclick="sortTable(0)" scope="col"><fmt:message key="entity.id"
                                                                    bundle="${var}"/></th>
                <th onclick="sortTable(1)" scope="col"><fmt:message key="book.title" bundle="${var}"/></th>
                <th onclick="sortTable(2)" scope="col"><fmt:message key="bookCopy.price_per_day" bundle="${var}"/></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bookListDto" items="${bookDto.copies}">
                <tr>
                    <td>
                        <c:out value="${bookListDto.id}"/>
                    </td>
                    <td>
                        <c:out value="${bookListDto.title}"/>
                    </td>
                    <td>
                        <c:out value="${bookListDto.pricePerDay}"/>
                    </td>
                    <td>
                        <div class="mt-3 d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/controller?command=book_copy_page&bookCopyId=${bookListDto.id}"
                               class="btn btn-dark btn-sm"><fmt:message key="btn.info"
                                                                        bundle="${var}"/></a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="main/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/tablePagination.js"></script>
<script src="${pageContext.request.contextPath}/js/tableSorting.js"></script>
</body>
</html>