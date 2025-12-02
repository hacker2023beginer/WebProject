<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Меню</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
        }
        nav {
            background: #333;
            padding: 10px;
        }
        nav a {
            color: #fff;
            text-decoration: none;
            margin-right: 15px;
            font-weight: bold;
        }
        nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<nav>
    <a href="${pageContext.request.contextPath}/index.jsp">Главная</a>
    <a href="${pageContext.request.contextPath}/login.jsp">Вход</a>
    <a href="${pageContext.request.contextPath}/register.jsp">Регистрация</a>
    <a href="${pageContext.request.contextPath}/competitions.jsp">Соревнования</a>
    <a href="${pageContext.request.contextPath}/users.jsp">Пользователи</a>

    <!-- Пример условного отображения -->
    <c:if test="${not empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/logout">Выход (${sessionScope.user.username})</a>
    </c:if>
</nav>

<h2>Добро пожаловать в систему тотализатора!</h2>
<p>Выберите пункт меню для продолжения работы.</p>
</body>
</html>
