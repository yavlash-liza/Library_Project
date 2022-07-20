<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <title><fmt:message key="header.bookCopy" bundle="${var}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
</head>
<%@include file="main/header.jsp" %>
<body>
<div class="container content bg">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4">
                <div class="d-flex flex-column align-items-sm-stretch text-center">
                    <img class="item" src="${pageContext.request.contextPath}${bookCopyDto.bookPhotoPath}"
                         alt="Card image cap" height="350" width="350">
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="bookCopy.title" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookCopyDto.title}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="bookCopy.published_year" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookCopyDto.publishedYear}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="bookCopy.price_per_day" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookCopyDto.pricePerDay}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="bookCopy.register_date" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookCopyDto.registerDate}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="bookCopy.status" bundle="${var}"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${bookCopyDto.copyStatus}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:choose>
        <c:when test="${sessionScope.role eq 'reader'}">
            <div class="d-grid row mx-auto">
                <div class="col-8 mt-3 center-block">
                    <a class="btn btn-danger btn-lg btn-block"
                       href="${pageContext.request.contextPath}/controller?command=add_book_copy_to_order_command&bookCopyId=${bookCopyDto.id}">
                        <fmt:message key="btn.add_to_order" bundle="${var}"/>
                    </a>
                </div>
            </div>
        </c:when>
        <c:when test="${sessionScope.role eq 'librarian'}">
            <div class="d-grid row mx-auto">
                <div class="col-6 mt-3 center-block">
                    <a class="btn btn-info btn-lg btn-block"
                       href="${pageContext.request.contextPath}/controller?command=update_book_copy_page&bookCopyId=${bookCopyDto.id}">
                        <fmt:message key="btn.change_info" bundle="${var}"/>
                    </a>
                </div>
            </div>
        </c:when>
    </c:choose>
</div>
</body>
<%@include file="main/footer.jsp" %>
</html>