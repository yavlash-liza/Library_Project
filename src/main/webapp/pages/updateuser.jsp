<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.reader" bundle="${var}"/></title>
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
                <form class="col-md-8 needs-validation" novalidate
                      action="${pageContext.request.contextPath}/controller?command=save_updated_user_command&userId=${user.id}"
                      method="post">
                    <input type="hidden" name="command" value="update">
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
                                        <h6 class="mb-0"><fmt:message key="sign_up.firstname" bundle="${var}"/></h6>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        <input type="text"
                                               class="form-control
                                            <c:choose>
                                               <c:when test="${empty requestScope.parameters}"></c:when>
                                               <c:when test="${empty requestScope.parameters.first_name}">is-invalid</c:when>
                                               <c:otherwise>is-valid</c:otherwise>
                                            </c:choose>"
                                               id="first_name" required name="first_name" minlength="2" maxlength="30"
                                               value="${user.firstName}"
                                               pattern="[А-ЯA-Z][а-яa-z]{2,29}"
                                               placeholder="${user.firstName}"
                                        >
                                    </div>
                                    <div class="invalid-feedback">
                                        <fmt:message key="sign_up.error.firstname" bundle="${var}"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <fmt:message key="sign_up.lastname" bundle="${var}"/>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        <input type="text"
                                               class="form-control
                                           <c:choose>
                                            <c:when test="${empty requestScope.parameters}"></c:when>
                                            <c:when test="${empty requestScope.parameters.last_name}">is-invalid</c:when>
                                            <c:otherwise>is-valid</c:otherwise>
                                           </c:choose>"
                                               id="last_name" required name="last_name" minlength="2" maxlength="30"
                                               value="${user.lastName}"
                                               pattern="[А-ЯA-Z][а-яa-z]{2,29}"
                                               placeholder="${user.lastName}"
                                        >
                                    </div>
                                    <div class="invalid-feedback">
                                        <fmt:message key="sign_up.error.lastname" bundle="${var}"/>
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
                                <hr>

                                <div class="row">
                                    <div class="col-sm-3">
                                        <fmt:message key="reader_registration.address" bundle="${var}"/>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        <input type="text"
                                               class="form-control
                                               <c:choose>
                                                <c:when test="${empty requestScope.parameters}"></c:when>
                                                <c:when test="${empty requestScope.parameters.address}">is-invalid</c:when>
                                                <c:otherwise>is-valid</c:otherwise>
                                               </c:choose>"
                                               id="address" required name="address"
                                               minlength="6"
                                               maxlength="40"
                                               value="${user.address}"
                                               pattern="[А-ЯA-Z][а-яa-z]+\s\d*"
                                                placeholder="${user.address}"
                                        >
                                    </div>
                                    <div class="invalid-feedback">
                                        <fmt:message key="reader_registration.error.address" bundle="${var}"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <fmt:message key="sign_up.error.birth_date" bundle="${var}"/>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        <input type="date"
                                               class="form-control
                                           <c:choose>
                                            <c:when test="${empty requestScope.parameters}"></c:when>
                                            <c:when test="${requestScope.unique_birth_date_error or empty requestScope.parameters.birth_date}">is-invalid</c:when>
                                            <c:otherwise>is-valid</c:otherwise>
                                           </c:choose>"
                                               id="birth_date" required name="birth_date"
                                               value="${user.birthDate}"
                                               placeholder="${user.birthDate}"
                                        >
                                    </div>
                                    <div class="invalid-feedback">
                                        <fmt:message key="sign_up.error.unique_birth_date" bundle="${var}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="d-grid row justify-content-center mx-auto">
                        <div class="col-12 mt-3">
                            <button class="btn btn-primary" type="submit">
                                <fmt:message key="btn.save_changes" bundle="${var}"/>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <br/>
    </div>
</div>

</div>
</body>
<%@include file="main/footer.jsp" %>
</html>