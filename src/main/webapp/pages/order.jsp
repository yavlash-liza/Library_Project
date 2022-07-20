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
<div class="container lign-self-end content bg">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4">
                <div class="d-flex flex-column align-items-center text-center">
                    <img class="item" src="${pageContext.request.contextPath}/images/order.png" alt="Card image cap"
                         height="250" width="250">
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="orders.id" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${orderDto.id}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="header.reader" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${orderDto.reader.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="orders.expirationdate" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${orderDto.expirationDate}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="orders.creationDate" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${orderDto.creationDate}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="orders.finalsum" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${orderDto.finalSum}$
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </div>

    <div class="bg">
        <div class="container lign-self-end">
            <h2><fmt:message key="reader.orderedBooks" bundle="${var}"/></h2>
            <c:forEach var="bookListDto" items="${orderDto.books}" varStatus="vs">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="width: 20rem;">
                        <a href="${pageContext.request.contextPath}/controller?command=book_page&bookId=${bookListDto.id}">
                            <h5><c:out value="${bookListDto.title}"/></h5>
                            <h5><c:out value="${bookListDto.pricePerDay}"/>$</h5>
                        </a>
                    </li>
                </ul>
            </c:forEach>
        </div>
    </div>

</div>
</body>
<%@include file="main/footer.jsp" %>
</html>