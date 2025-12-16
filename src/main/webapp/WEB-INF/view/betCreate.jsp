<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Place Bet</title></head>
<body>
<h1>Place a Bet</h1>
<form action="/bet" method="post">
    <label>Competition ID:</label>
    <input type="text" name="competitionId" required/><br/>

    <label>Bet Type:</label>
    <select name="betType">
        <option value="HOME_WIN">Home Win</option>
        <option value="DRAW">Draw</option>
        <option value="AWAY_WIN">Away Win</option>
    </select><br/>

    <label>Amount:</label>
    <input type="number" step="0.01" name="amount" required/><br/>

    <input type="submit" value="Place Bet"/>
</form>
<a href="/bet">Back to Bets</a>
</body>
</html>
