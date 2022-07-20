<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="headers.book_copy_add" bundle="${var}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body class="d-flex flex-column min-vh-100">
<div class="content">
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=add_copies"
          class="container g-3 needs-validation justify-content-center" novalidate>
        <input type="hidden" name="command" value="sign_up">
        <div class="row text-center">
            <div class="col-md-12">
                <h1><fmt:message key="book_copy_add.title" bundle="${var}"/></h1>
                <p><fmt:message key="book_copy_add.msg" bundle="${var}"/></p>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-5">
                <label for="published_year" class="form-label">
                    <fmt:message key="book_copy_add.published_year" bundle="${var}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.published_year}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="published_year" required name="published_year"
                       min="0"
                       value="${requestScope.parameters.get("published_year")}"
                       pattern="\d{1,4}">
                <div class="invalid-feedback">
                    <fmt:message key="book_copy_add.error.published_year" bundle="${var}"/>
                </div>
            </div>
            <br>
            <div class="col-md-4">
                <label for="price_per_day" class="form-label">
                    <fmt:message key="book_copy_add.price_per_day" bundle="${var}"/>
                </label>
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
                       value="${requestScope.parameters.get("price_per_day")}"
                       pattern="(\d+[,\\.]\d{1,2})">
                <div class="invalid-feedback">
                    <fmt:message key="book_copy_add.error.price_per_day" bundle="${var}"/>
                </div>
            </div>
            <br>
            <br>
            <div class="col-md-5">
                <label for="count" class="form-label">
                    <fmt:message key="book_copy_add.count" bundle="${var}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.count}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="count" required name="count"
                       min="0"
                       value="${requestScope.parameters.get("count")}"
                       pattern="\d{1,4}">
                <div class="invalid-feedback">
                    <fmt:message key="book_copy_add.error.count" bundle="${var}"/>
                </div>
            </div>
            <br>
        </div>
        <div class="d-grid row justify-content-center mx-auto">
            <div class="mt-3">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="reader_registration.submit" bundle="${var}"/>
                </button>
            </div>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/imageDisplay.js"></script>
<script src="${pageContext.request.contextPath}/js/readerRegistration.js"></script>
</body>
<%@include file="main/footer.jsp" %>
</html>