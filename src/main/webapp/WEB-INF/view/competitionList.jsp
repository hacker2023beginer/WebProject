<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Competitions</title></head>
<body>
<h1>Competitions</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Home Team</th>
        <th>Away Team</th>
        <th>Date</th>
        <th>Status</th>
        <th>Result</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="competition" items="${competitions}">
        <tr>
            <td>${competition.id}</td>
            <td>${competition.teamHome}</td>
            <td>${competition.teamAway}</td>
            <td>${competition.date}</td>
            <td>${competition.status}</td>
            <td>${competition.result}</td>
            <td>
                <a href="/competition?action=edit&id=${competition.id}">Edit</a> |
                <a href="/competition?action=delete&id=${competition.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/competition?action=create">Create new competition</a>
</body>
</html>
