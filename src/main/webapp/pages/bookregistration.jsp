<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="headers.book_registration" bundle="${var}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body class="d-flex flex-column min-vh-100">
<div class="content">
    <br/>
    <form method="post" action="${pageContext.request.contextPath}/controller?command=book_registration"
          class="container g-3 needs-validation" novalidate>
        <div class="input-group justify-content-center">
            <div class="row text-center">
                <div class="col-md-12">
                    <h2><fmt:message key="book_registration.title" bundle="${var}"/></h2>
                </div>
            </div>

            <div class="col-md-10">
                <label for="title" class="form-label">
                    <fmt:message key="book_registration.book_title" bundle="${var}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.title}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="title" required name="title" minlength="2" maxlength="256"
                       value="${requestScope.parameters.get("title")}"
                       pattern="^[А-ЯA-Zа-яa-z0-9]+([-_\s]{1}[А-ЯA-Zа-яa-z0-9]+)*$">
                <div class="invalid-feedback">
                    <fmt:message key="book_registration.error.title" bundle="${var}"/>
                </div>
            </div>

            <div class="col-md-10">
                <label for="author" class="form-label">
                    <fmt:message key="book_registration.author" bundle="${var}"/>
                </label>
                <select class="custom-select" id="author" name="author">
                    <option value="${selected}" selected>
                        <fmt:message key="book_registration.author.title" bundle="${var}"/>
                    </option>
                    <c:forEach var="author" items="${authorList}">
                        <option value="${author.id}">
                                ${author.lastName} ${author.firstName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-md-10">
                <label for="genre" class="form-label">
                    <fmt:message key="book_registration.genre.title" bundle="${var}"/>
                </label>
                <select class="custom-select" id="genre" name="genre">
                    <option value="${selected}" selected>
                        <fmt:message key="book_registration.genre" bundle="${var}"/>
                    </option>
                    <c:forEach var="genre" items="${genreList}">
                        <option value="${genre.id}">
                                ${genre.genreName}
                        </option>
                    </c:forEach>
                </select>
            </div>


            <div class="row justify-content-center">
                <div class="col-md-8">
                    <label for="pages" class="form-label">
                        <fmt:message key="book_registration.pages" bundle="${var}"/>
                    </label>
                    <input type="text"
                           class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.pages}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                           id="pages" required name="pages"
                           min="0"
                           value="${requestScope.parameters.get("pages")}"
                           pattern="\d{1,4}">
                    <div class="invalid-feedback">
                        <fmt:message key="book_registration.error.pages" bundle="${var}"/>
                    </div>
                </div>

                <div class="col-md-8">
                    <label for="release_year" class="form-label">
                        <fmt:message key="book_registration.release_year" bundle="${var}"/>
                    </label>
                    <input type="text"
                           class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.release_year}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                           id="release_year" required name="release_year"
                           min="0"
                           value="${requestScope.parameters.get("release_year")}"
                           pattern="\d{1,4}">
                    <div class="invalid-feedback">
                        <fmt:message key="book_registration.error.release_year" bundle="${var}"/>
                    </div>
                </div>
            </div>

            <div class="row text-center">
            <div class="col-md-10">
                <div class="col-12 mt-3">
                    <button class="btn-lg btn-primary" type="submit">
                        <fmt:message key="reader_registration.submit" bundle="${var}"/>
                    </button>
                </div>
            </div>
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