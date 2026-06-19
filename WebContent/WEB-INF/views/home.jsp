<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Midterms</title>
</head>
<body>
<c:choose>
    <c:when test="${username != null}">
        Congratulations ${username}! You have completed midterms.
    </c:when>
    <c:when test="${username == null}">
        Nice try. But not yet.
    </c:when>
</c:choose>
</body>
</html>