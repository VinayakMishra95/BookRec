<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Book search</title>
    <link rel="stylesheet" type="text/css" href="../css/form.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">

    <style>
        body.dark-mode {
            background-color: #121212;
            color: #ffffff;
        }

        .theme-toggle-button {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            cursor: pointer;
            font-size: 16px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
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

<button class="theme-toggle-button" onclick="toggleTheme()">☀️</button>

<script src="${pageContext.request.contextPath}/js/theme1.js"></script>
</div>
</body>
</html>
