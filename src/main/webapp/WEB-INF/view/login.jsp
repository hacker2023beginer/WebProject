<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
<h1>Login Page</h1>
<form action="/app?action=login" method="post">
    <input type="hidden" name="action" value="register"/>
    <label>Username:</label>
    <input type="text" name="username" required/><br/>

    <label>Password:</label>
    <input type="password" name="password" required/><br/>
    <input type="submit" value="Login"/>
</form>
<a href="/app?action=register">Have not any account? Register</a> |
<a href="/app?action=home">Home</a>
</body>
</html>
