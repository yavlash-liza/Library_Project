<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/4.1.5/css/flag-icons.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">

    <title><fmt:message key="headers.signup" bundle="${var}"/></title>
</head>
<%@include file="main/header.jsp" %>
<body class="d-flex flex-column min-vh-100">
<div class="container py-5 h-100">
    <div class="row d-flex align-items-center justify-content-center h-100">
        <div class="col-md-8 col-lg-7 col-xl-6">
            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
                 class="img-fluid" alt="Phone image" width="350" height="350">
        </div>
    </div>
</div>
<br>
<main>
    <form class="container g-3 needs-validation justify-content-center " novalidate
          action="${pageContext.request.contextPath}/controller?command=sign_up&roleId=1" method="post">
        <input type="hidden" name="command" value="sign_up">

        <div class="row text-center">
            <div class="col-md-12">
                <h2><fmt:message key="sign_up.title" bundle="${var}"/></h2>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <label for="first_name" class="form-label">
                    <fmt:message key="sign_up.firstname" bundle="${var}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.first_name}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="first_name" required name="first_name" minlength="2" maxlength="30"
                       value="${requestScope.parameters.get("first_name")}"
                       pattern="[А-ЯA-Z][а-яa-z]{2,29}">
                <div class="invalid-feedback">
                    <fmt:message key="sign_up.error.firstname" bundle="${var}"/>
                </div>
            </div>

            <div class="col-md-8">
                <label for="last_name" class="form-label">
                    <fmt:message key="sign_up.lastname" bundle="${var}"/>
                </label>
                <input type="text"
                       class="form-control
                       <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.last_name}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                       </c:choose>"
                       id="last_name" required name="last_name" minlength="2" maxlength="30"
                       value="${requestScope.parameters.get("last_name")}"
                       pattern="[А-ЯA-Z][а-яa-z]{2,29}">
                <div class="invalid-feedback">
                    <fmt:message key="sign_up.error.lastname" bundle="${var}"/>
                </div>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <label for="email" class="form-label">
                    <fmt:message key="sign_up.email" bundle="${var}"/>
                </label>
                <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend1">
                    <i class="bi bi-envelope"></i>
                </span>
                    <input type="email"
                           class="form-control
                           <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${requestScope.unique_email_error or empty requestScope.parameters.email}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                           </c:choose>"
                           id="email" required name="email"
                           minlength="6"
                           maxlength="40"
                           value="${requestScope.parameters.get("email")}"
                           pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.email" bundle="${var}"/>
                        <c:if test="${requestScope.unique_email_error}">
                            <span class="signup-unique">
                                <fmt:message key="sign_up.error.unique_email" bundle="${var}"/>
                            </span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <label for="birth_date" class="form-label">
                    <fmt:message key="sign_up.email" bundle="${var}"/>
                </label>
                <input type="date"
                       class="form-control
                           <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${requestScope.unique_birth_date_error or empty requestScope.parameters.birth_date}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                           </c:choose>"
                       id="birth_date" required name="birth_date"
                       value="${requestScope.parameters.get("birth_date")}">
                <div class="invalid-feedback">
                    <fmt:message key="sign_up.error.birth_date" bundle="${var}"/>
                    <c:if test="${requestScope.unique_birth_date_error}">
                            <span class="signup-unique">
                                <fmt:message key="sign_up.error.unique_birth_date" bundle="${var}"/>
                            </span>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <label for="validationPass" class="form-label">
                    <fmt:message key="sign_up.password" bundle="${var}"/></label>
                <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend3">
                            <i class="bi bi-key"></i>
                        </span>
                    <input type="password"
                           class="form-control"
                           id="validationPass" required
                           aria-describedby="passwordHelpBlock" minlength="6" maxlength="30" name="password"
                           pattern="[A-Za-z\d\.\-_&%$#@!*,]{6,30}">
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.password" bundle="${var}"/>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <label for="validationRepeatPass" class="form-label">
                    <fmt:message key="sign_up.repeated_password" bundle="${var}"/>
                </label>
                <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend4">
                <i class="bi bi-key-fill"></i>
                  </span>
                    <input type="password" class="form-control" id="validationRepeatPass" required
                           minlength="6"
                           maxlength="30">
                    <div class="invalid-feedback">
                        <fmt:message key="sign_up.error.confirmpassword" bundle="${var}"/>
                    </div>
                </div>
            </div>
            <div id="passwordHelpBlock" class="form-text text-center">
                <fmt:message key="sign_up.password.help" bundle="${var}"/>
            </div>
        </div>

        <div class="col-md-12">
            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                    <label class="form-check-label" for="invalidCheck">
                        Agree to terms and conditions
                    </label>
                    <div class="invalid-feedback">
                        You must agree before submitting.
                    </div>
                </div>
            </div>
        </div>

        <div class="d-grid row justify-content-center mx-auto">
            <div class="col-12 mt-3">
                <button class="btn btn-primary" type="submit">
                    <fmt:message key="sign_up.submit" bundle="${var}"/>
                </button>
            </div>
        </div>

    </form>
</main>
<%@include file="main/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/signUpValidation.js"></script>
</body>
</html>