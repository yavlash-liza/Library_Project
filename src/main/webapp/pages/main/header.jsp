<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title>Header</title>
    <meta charset="UTF-8">
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=go_to_home_page">
            <img src="${pageContext.request.contextPath}/images/logo.png" width="30" height="30"
                 class="d-inline-block align-top" alt="logo">
            <fmt:message key="header.title" bundle="${var}"/>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="true" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="navbar-collapse collapse show" id="navbarColor01">
            <c:choose>
                <c:when test="${sessionScope.role eq 'admin'}">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="${pageContext.request.contextPath}/controller?command=go_to_home_page"><fmt:message
                                    key="header.home" bundle="${var}"/>
                                <span class="visually-hidden"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=users_page"><fmt:message
                                    key="header.users"
                                    bundle="${var}"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=sign_out"><fmt:message
                                    key="headers.signout"
                                    bundle="${var}"/></a>
                        </li>
                    </ul>
                    <ul class="navbar-nav justify-content-end">
                        <li class="nav-item">
                            <button type="button"
                                    class="btn btn-outline-warning btn-sm d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/controller?command=user_page&userId=${sessionScope.user.id}">
                                    <fmt:message key="header.account" bundle="${var}"/>
                                </a>
                            </button>
                        </li>
                    </ul>
                </c:when>

                <c:when test="${sessionScope.role eq 'librarian'}">
                    <ul class="navbar-nav me-auto">

                        <li class="nav-item">
                            <a class="nav-link active"
                               href="${pageContext.request.contextPath}/controller?command=go_to_home_page"><fmt:message
                                    key="header.home" bundle="${var}"/>
                                <span class="visually-hidden"></span>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=books_page"><fmt:message
                                    key="header.books"
                                    bundle="${var}"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_book_registration_page"><fmt:message
                                    key="headers.book_registration" bundle="${var}"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=readers_page"><fmt:message
                                    key="header.readers"
                                    bundle="${var}"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=orders_page"><fmt:message
                                    key="header.orders"
                                    bundle="${var}"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=sign_out"><fmt:message
                                    key="headers.signout"
                                    bundle="${var}"/></a>
                        </li>
                    </ul>
                    <ul class="navbar-nav justify-content-end">
                        <li class="nav-item">
                            <button type="button"
                                    class="btn btn-outline-warning btn-sm d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/controller?command=user_page&userId=${sessionScope.user.id}">
                                    <fmt:message key="header.account" bundle="${var}"/>
                                </a>
                            </button>
                        </li>
                    </ul>
                </c:when>

                <c:when test="${sessionScope.role eq 'reader'}">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="${pageContext.request.contextPath}/controller?command=go_to_home_page"><fmt:message
                                    key="header.home" bundle="${var}"/>
                                <span class="visually-hidden"></span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=books_page"><fmt:message
                                    key="header.books"
                                    bundle="${var}"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=sign_out"><fmt:message
                                    key="headers.signout"
                                    bundle="${var}"/></a>
                        </li>
                    </ul>
                    <ul class="navbar-nav justify-content-end">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_cart_page">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-bag-heart-fill" viewBox="0 0 16 16">
                                    <path d="M11.5 4v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5ZM8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1Zm0 6.993c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132Z"/>
                                </svg>
                            </a>
                        </li>
                        <li class="nav-item">
                            <button type="button"
                                    class="btn btn-outline-warning btn-sm d-none d-lg-inline-block mb-3 mb-md-0 ml-md-3">
                                <a class="nav-link"
                                   href="${pageContext.request.contextPath}/controller?command=user_page&userId=${sessionScope.user.id}">
                                    <fmt:message key="header.account" bundle="${var}"/>
                                </a>
                            </button>
                        </li>
                    </ul>
                </c:when>

                <c:otherwise>
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <a class="nav-link active"
                               href="${pageContext.request.contextPath}/controller?command=go_to_home_page"><fmt:message
                                    key="header.home" bundle="${var}"/>
                                <span class="visually-hidden"></span>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_sign_in_page"><fmt:message
                                    key="headers.signin"
                                    bundle="${var}"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/controller?command=go_to_sign_up_page"><fmt:message
                                    key="headers.signup"
                                    bundle="${var}"/></a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<script src="//cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/0.10.0/ui-bootstrap-tpls.min.js"></script>
</body>
</html>