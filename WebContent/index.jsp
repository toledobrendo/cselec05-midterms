<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Midterms</title>
</head>
<body>
<h3>Login</h3>
<c:choose>
    <c:when test="${success != null}">
        Registration success! You can now login.
    </c:when>
</c:choose>
<form action="/midterms/login" method="post">
    Username: <input type="text" name=""/><br/>
    Password: <input type="password" name="password"/><br/>
    <input type="submit"/>
    <button><a href="registration-form.jsp">Register</a></button>
</form>
<c:choose>
    <c:when test="${error != null}">
        <br/>
        ${error}
    </c:when>
</c:choose>
</body>
</html>