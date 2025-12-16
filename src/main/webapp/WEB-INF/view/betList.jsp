<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Your Bets</title></head>
<body>
<h1>Your Bets</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Competition</th>
        <th>Type</th>
        <th>Amount</th>
        <th>Coefficient</th>
        <th>Status</th>
    </tr>
    <c:forEach var="bet" items="${bets}">
        <tr>
            <td>${bet.id}</td>
            <td>${bet.competitionId}</td>
            <td>${bet.betType}</td>
            <td>${bet.amount}</td>
            <td>${bet.coefficient}</td>
            <td>${bet.status}</td>
        </tr>
    </c:forEach>
</table>
<a href="/bet?action=create">Place new bet</a>
</body>
</html>
