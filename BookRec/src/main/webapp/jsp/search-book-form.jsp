<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Book search</title>
    <link rel="stylesheet" type="text/css" href="../css/form.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<header>
    	<c:import url="navbar.jsp"/>
	</header>
	<div class="mainbody">
		<h1>Search for a book:</h1>
		<form method="POST" action="<c:url value='/book'/>">
    		<label for="titleID">Name:</label>
    		<input id="titleID" name="title" type="text"/><br/><br/>
    		<button type="submit">Submit</button><br/>
    		<button type="reset">Reset the form</button>
		</form>
		<button id="theme">🌙</button>
	</div>
	<script src="../js/theme8.js"></script>
</body>
</html>
