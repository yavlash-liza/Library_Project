<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.default" bundle="${var}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<%@include file="header.jsp" %>
<body>
<div class="container content bg">
    <br/>
    <div class="main-body">
        <div class="grid">
            <div class="col-6">
                <img class="item" src="${pageContext.request.contextPath}/images/bro.jpg" alt="Card image cap"
                     width="485"
                     height="411">
            </div>
            <div class="col-6">
                <h1><fmt:message key="default.text" bundle="${var}"/></h1>
                <br>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller?command=go_to_home_page">
                    <fmt:message key="btn.go_to_home" bundle="${var}"/>
                </a>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
