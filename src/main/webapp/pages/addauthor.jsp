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

  <title><fmt:message key="header.add_author" bundle="${var}"/></title>

</head>
<jsp:include page="main/header.jsp"/>
<body class="d-flex flex-column min-vh-100">
<div class="content">
  <br>
  <form class="container g-3 needs-validation justify-content-center" novalidate
        action="${pageContext.request.contextPath}/controller?command=add_author" method="post">
    <input type="hidden" name="command" value="reader_registration">

    <div class="row text-center">
      <div class="col-md-12">
        <h3><fmt:message key="header.add_author" bundle="${var}"/></h3>
      </div>
    </div>
    <br>
    <div class="row justify-content-center">
      <div class="col-md-3">
        <label for="first_name" class="form-label">
          <fmt:message key="reader_registration.firstname" bundle="${var}"/>
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
               pattern="[А-ЯA-Z][а-яa-z]{1,64}">
        <div class="invalid-feedback">
          <fmt:message key="genre.add.error.firstname" bundle="${var}"/>
        </div>
      </div>

      <div class="col-md-3">
        <label for="last_name" class="form-label">
          <fmt:message key="reader_registration.lastname" bundle="${var}"/>
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
               pattern="[А-ЯA-Z][а-яa-z]{1,64}">
        <div class="invalid-feedback">
          <fmt:message key="genre.add.error.lastname" bundle="${var}"/>
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

  </form>
</div>
<%@include file="main/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/readerRegistration.js"></script>
</body>
</html>