<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.update_bookCopy" bundle="${var}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body>
<div class="container content bg">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4">
                <div class="d-flex flex-column align-items-sm-stretch text-center">
                    <img class="item" src="${pageContext.request.contextPath}${bookCopyDto.bookPhotoPath}"
                         alt="Card image cap" height="350" width="350">
                </div>
            </div>
            <form class="col-md-8 needs-validation" novalidate
                  action="${pageContext.request.contextPath}/controller?command=save_updated_book_copy_command&bookCopyId=${bookCopyDto.id}"
                  method="post">
                <input type="hidden" name="command" value="update">
                <div class="col-md-8">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="bookCopy.title" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${bookCopyDto.title}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <fmt:message key="bookCopy.published_year" bundle="${var}"/>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="text"
                                           class="form-control
                                   <c:choose>
                                    <c:when test="${empty requestScope.parameters}"></c:when>
                                    <c:when test="${empty requestScope.parameters.published_year}">is-invalid</c:when>
                                    <c:otherwise>is-valid</c:otherwise>
                                   </c:choose>"
                                           id="release_year" required name="published_year"
                                           min="0"
                                           value="${bookCopyDto.publishedYear}"
                                           pattern="\d{1,4}"
                                           placeholder="${bookCopyDto.publishedYear}">
                                </div>
                                <div class="invalid-feedback">
                                    <fmt:message key="book_copy_add.error.published_year" bundle="${var}"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <fmt:message key="bookCopy.price_per_day" bundle="${var}"/>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <input type="number"
                                           class="form-control
                                       <c:choose>
                                        <c:when test="${empty requestScope.parameters}"></c:when>
                                        <c:when test="${empty requestScope.parameters.price_per_day}">is-invalid</c:when>
                                        <c:otherwise>is-valid</c:otherwise>
                                       </c:choose>"
                                           id="price_per_day" required
                                           name="price_per_day"
                                           min="0"
                                           value="${bookCopyDto.pricePerDay}"
                                           pattern="(\d+[,\\.]\d{1,2})"
                                           placeholder="${bookCopyDto.pricePerDay}">
                                </div>
                                <div class="invalid-feedback">
                                    <fmt:message key="book_copy_add.error.price_per_day" bundle="${var}"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="bookCopy.register_date"
                                                                  bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${bookCopyDto.registerDate}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="bookCopy.status" bundle="${var}"/></h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${bookCopyDto.copyStatus}
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
    </div>
</div>
</body>
<%@include file="main/footer.jsp" %>
</html>