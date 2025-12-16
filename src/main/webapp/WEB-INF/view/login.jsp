<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
<h1>Login Page</h1>
<form action="${pageContext.request.contextPath}/app" method="post">
    <input type="hidden" name="action" value="login"/>
    <label>Username:</label>
    <input type="text" name="username" required/><br/>

    <label>Password:</label>
    <input type="password" name="password" required/><br/>
    <input type="submit" value="Login"/>
</form>

<a href="${pageContext.request.contextPath}/app?action=register">Have not any account? Register</a> |
<a href="${pageContext.request.contextPath}/app?action=home">Home</a>
</body>
</html>
