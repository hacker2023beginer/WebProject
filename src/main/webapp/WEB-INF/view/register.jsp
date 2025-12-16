<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Register</title></head>
<body>
<h1>User Registration</h1>

<form action="${pageContext.request.contextPath}/app" method="post">
    <input type="hidden" name="action" value="register"/>
    <label>Username:</label>
    <input type="text" name="username" required/><br/>

    <label>Password:</label>
    <input type="password" name="password" required/><br/>

    <input type="submit" value="Register"/>
</form>

<a href="${pageContext.request.contextPath}/app?action=login">Already have an account? Login</a> |
<a href="${pageContext.request.contextPath}/app?action=home">Home</a>
</body>
</html>
