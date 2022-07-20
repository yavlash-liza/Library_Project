<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/WEB-INF/customTag.tld" prefix="ctg" %>
<html>
<head>
    <title>Footer</title>
    <meta charset="UTF-8">
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<body>
<footer class="page-footer font-small special-color-dark pt-4">
    <div class="footer-copyright py-3">
        <div class="group">
            <section>
                <div class="center-tag">
                    <div class="tag">
                        <ctg:emptyTag/>
                    </div>
                    <p>© 2022 Copyright: Yavlash.com</p>
                </div>
            </section>
            <aside>
                <div id="language">
                    <p><fmt:message key="footer.language" bundle="${var}"/> ${sessionScope.language}</p>
                    <div class="languages">
                        <p>
                            <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=EN">RU</a>
                        </p>
                        <p>
                            <a href="${pageContext.request.contextPath}/controller?command=change_locale&language=RU">EN</a>
                        </p>
                    </div>
                </div>
            </aside>
        </div>
    </div>
</footer>
</body>
</html>