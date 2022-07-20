<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.book" bundle="${var}"/></title>
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
            <form class="col-md-8 needs-validation" novalidate
                  action="${pageContext.request.contextPath}/controller?command=update_book&bookId=${bookDto.id}"
                  method="post">
                <input type="hidden" name="command" value="update">
                <div class="col-md-8">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <fmt:message key="book_registration.book_title" bundle="${var}"/>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text"
                                           class="form-control
                                       <c:choose>
                                        <c:when test="${empty requestScope.parameters}"></c:when>
                                        <c:when test="${empty requestScope.parameters.title}">is-invalid</c:when>
                                        <c:otherwise>is-valid</c:otherwise>
                                       </c:choose>"
                                           id="title" required name="title" minlength="2" maxlength="256"
                                           value="${bookDto.title}"
                                           pattern="^[А-ЯA-Zа-яa-z0-9]+([-_\s]{1}[А-ЯA-Zа-яa-z0-9]+)*$"
                                           placeholder="${bookDto.title}">
                                </div>
                                <div class="invalid-feedback">
                                    <fmt:message key="book_registration.error.title" bundle="${var}"/>
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
                                    <fmt:message key="book.releaseyear" bundle="${var}"/>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text"
                                           class="form-control
                                   <c:choose>
                                    <c:when test="${empty requestScope.parameters}"></c:when>
                                    <c:when test="${empty requestScope.parameters.release_year}">is-invalid</c:when>
                                    <c:otherwise>is-valid</c:otherwise>
                                   </c:choose>"
                                           id="release_year" required name="release_year"
                                           min="0"
                                           value="${bookDto.releaseYear}"
                                           pattern="\d{1,4}"
                                           placeholder="${bookDto.releaseYear}">
                                </div>
                                <div class="invalid-feedback">
                                    <fmt:message key="book_registration.error.release_year" bundle="${var}"/>
                                </div>
                            </div>
                            <hr>

                            <div class="row">
                                <div class="col-sm-3">
                                    <fmt:message key="book.pages" bundle="${var}"/>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text"
                                           class="form-control
                                   <c:choose>
                                    <c:when test="${empty requestScope.parameters}"></c:when>
                                    <c:when test="${empty requestScope.parameters.pages}">is-invalid</c:when>
                                    <c:otherwise>is-valid</c:otherwise>
                                   </c:choose>"
                                           id="pages" required name="pages"
                                           min="0"
                                           value="${bookDto.pages}"
                                           pattern="\d{1,4}"
                                           placeholder="${bookDto.pages}">
                                </div>
                                <div class="invalid-feedback">
                                    <fmt:message key="book_registration.error.pages" bundle="${var}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <div class="d-grid row justify-content-center mx-auto">
                    <div class="col-12 mt-3">
                        <button class="btn btn-primary" type="submit">
                            <fmt:message key="reader_registration.submit" bundle="${var}"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <br/>
    </div>

</div>
</body>
<%@include file="main/footer.jsp" %>
</html>