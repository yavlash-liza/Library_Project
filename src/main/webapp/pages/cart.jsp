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
    <meta charset="UTF-8">

    <title><fmt:message key="order.title" bundle="${var}"/></title>

</head>
<jsp:include page="main/header.jsp"/>
<body class="d-flex flex-column min-vh-100">
<div class="content">
    <br>
    <c:choose>
        <c:when test="${empty requestScope.bookCopyListDto}">
            <div class="container">
                <div class="empty-cart">
                    <div class="col-md-4">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img class="item" src="${pageContext.request.contextPath}/images/Cart-Transparent-PNG.png"
                                 alt="Card image cap" height="250" width="250">
                        </div>
                    </div>
                    <h1 class="d-flex text-center">
                        <fmt:message key="msg.empty_cart" bundle="${var}"/>
                    </h1>
                    <p>
                        <fmt:message key="msg2.empty_cart" bundle="${var}"/>
                    </p>
                    <div class="d-grid  d-md-flex justify-content-md-center">
                        <a class="btn btn-dark btn-lg btn-block"
                           href="${pageContext.request.contextPath}/controller?command=books_page"><fmt:message
                                key="btn.go_to_books"
                                bundle="${var}"/></a>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <form class="container g-3 needs-validation justify-content-center" novalidate
                  action="${pageContext.request.contextPath}/controller?command=add_order&userId=${user.id}"
                  method="post">
                <input type="hidden" name="command" value="order">

                <div class="row text-center">
                    <div class="col-md-12">
                        <h2><fmt:message key="cart.title" bundle="${var}"/></h2>
                    </div>
                </div>

                <div class="cart-wrapper">
                    <div class="col-md-6 order-md-2 mb-4">
                        <ul class="list-group mb-3 sticky-top">
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0"><fmt:message key="bookCopy.title" bundle="${var}"/></h6>
                                </div>
                                <span class="my-0"><fmt:message key="bookCopy.price_per_day" bundle="${var}"/></span>
                            </li>
                            <c:forEach var="bookCopyListDto" items="${bookCopyListDto}">
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0"><c:out value="${bookCopyListDto.title}"/></h6>
                                    </div>
                                    <span class="text-muted"><c:out value="${bookCopyListDto.pricePerDay}"/>$</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="col-md-4">
                        <label for="rent_days" class="form-label">
                            <fmt:message key="order.rent_days" bundle="${var}"/>
                        </label>
                        <div class="input-group has-validation">
                            <input type="text"
                                   class="form-control
                           <c:choose>
                        <c:when test="${empty requestScope.parameters}"></c:when>
                        <c:when test="${empty requestScope.parameters.rent_days}">is-invalid</c:when>
                        <c:otherwise>is-valid</c:otherwise>
                           </c:choose>"
                                   id="rent_days" required name="rent_days"
                                   value="${requestScope.parameters.get("rent_days")}">
                            <div class="invalid-feedback">
                                <fmt:message key="order.error.rent_days" bundle="${var}"/>
                            </div>
                        </div>
                    </div>

                    <div class="d-grid row justify-content-center mx-auto">
                        <div class="col-12 mt-3">
                            <button class="btn btn-primary" type="submit">
                                <fmt:message key="reader_registration.submit" bundle="${var}"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="main/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/readerRegistration.js"></script>
</body>
</html>