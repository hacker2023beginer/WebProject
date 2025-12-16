<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8d7da; color: #721c24; }
        .error-box {
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #f5c6cb;
            background-color: #f8d7da;
            width: 60%;
            text-align: center;
            border-radius: 8px;
        }
        h1 { margin-bottom: 20px; }
        a { color: #004085; text-decoration: none; }
        a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="error-box">
    <h1>Something went wrong!</h1>
    <p>${errorMessage}</p>
    <p>Please try again later or contact support.</p>
    <a href="${pageContext.request.contextPath}/app?action=home">Back to Home</a>
</div>
</body>
</html>
