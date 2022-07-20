<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">

    <title><fmt:message key="headers.signin" bundle="${var}"/></title>
</head>
<%@include file="main/header.jsp" %>
<body class="d-flex flex-column min-vh-100">
<main class="form-signin text-center content">
    <form method="post" action="${pageContext.request.contextPath}/controller?command=sign_in" class="needs-validation"
          novalidate>
        <input type="hidden" name="command" value="log_in">

        <h1 class="h3 mb-3 fw-normal">
            <fmt:message key="signin.title" bundle="${var}"/>
        </h1>

        <div class="form-floating mb-2">
            <label for="floatingInput">
                <fmt:message key="signin.email" bundle="${var}"/>
            </label>
            <input type="email" class="form-control
             <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${requestScope.unique_email_error or empty requestScope.parameters.email}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                           </c:choose>"
                   id="floatingInput"
                   placeholder="name@example.com"
                   required
                   maxlength="40"
                   minlength="4"
                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                   name="email"
                   value=${requestScope.parameters.get("email")}>
            <div class="invalid-feedback">
                <fmt:message key="signin.error.email" bundle="${var}"/>
            </div>
        </div>

        <div class="form-floating">
            <label for="floatingPassword">
                <fmt:message key="signin.password" bundle="${var}"/>
            </label>
            <input type="password" class="form-control" id="floatingPassword"
                   name="password"
                   placeholder="Password"
                   required
                   maxlength="30"
                   minlength="6"
                   pattern="[A-Za-z\d\.\-_&%$#@!*,]{6,30}">
            <div class="invalid-feedback">
                <fmt:message key="signin.error.password" bundle="${var}"/>
            </div>
        </div>

        <div class="d-grid row justify-content-center mx-auto">
            <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="signin.submit" bundle="${var}"/>
                </button>
            </div>
        </div>

        <hr class="my-3">
        <div class="d-grid  d-md-flex justify-content-md-center">
            <a class="btn btn-dark btn-lg btn-block"
               href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page">
                <fmt:message key="signin.sign_up" bundle="${var}"/>
            </a>
        </div>

    </form>
    <c:if test="${not empty param.m}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="signin.error.auth" bundle="${var}"/>
        </div>
    </c:if>
</main>
<%@include file="main/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/signUpValidation.js"></script>
</body>
</html>