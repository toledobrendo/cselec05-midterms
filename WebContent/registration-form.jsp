<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Midterms</title>
</head>
<body>
<h3>Register</h3>
<form action="/midterms/register" method="post">
    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name=""/><br/>
    <input type="submit"/>
</form>
<c:choose>
    <c:when test="${error != null}">
        <br/>
        ${error}
    </c:when>
</c:choose>
</body>
</html>