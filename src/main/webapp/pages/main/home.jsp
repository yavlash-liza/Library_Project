<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagetext" var="var"/>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">

    <title><fmt:message key="header.home" bundle="${var}"/></title>
</head>
<jsp:include page="header.jsp"/>
<body>
<div class="content">
    <section class="gallery-block cards-gallery">
        <div class="container">
            <div class="row">
                <c:forEach var="bookListDto" items="${bookListDto}" varStatus="vs">
                    <div class="col-md-6 col-lg-4">
                        <div class="card border-0 transform-on-hover">
                            <div class="photo">
                                <a class="lightbox">
                                    <img src="${pageContext.request.contextPath}${bookListDto.bookPhotoPath}"
                                         alt="Card Image"
                                         class="card-img-top">
                                </a>
                            </div>
                            <div class="card-body">
                                <h6>
                                    <a href="${pageContext.request.contextPath}/controller?command=book_page&bookId=${bookListDto.id}"><c:out
                                            value="${bookListDto.title}"/></a></h6>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.js"></script>
<script>
    baguetteBox.run('.cards-gallery', {animation: 'slideIn'});
</script>

<jsp:include page="footer.jsp"/>
</body>
</html>