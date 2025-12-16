<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Menu</title></head>
<body>
<h1>Main Menu</h1>

<ul>
    <li><a href="${pageContext.request.contextPath}/app?action=home">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/app?action=login">Login</a></li>
    <li><a href="${pageContext.request.contextPath}/app?action=register">Register</a></li>
    <li><a href="${pageContext.request.contextPath}/competition">Competitions</a></li>
    <li><a href="${pageContext.request.contextPath}/bet">Bets</a></li>
    <li><a href="${pageContext.request.contextPath}/app?action=users">Users</a></li>
</ul>

</body>
</html>
