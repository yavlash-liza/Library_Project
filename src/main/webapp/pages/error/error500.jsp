<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error 500</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:200,400,700" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}css/style.css"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404">
            <h1>Oops!</h1>
            <h2><fmt:message key="error.500" bundle="${var}"/></h2>
        </div>
        <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="error.back" bundle="${var}"/></a>
    </div>
</div>
</body>
</html>