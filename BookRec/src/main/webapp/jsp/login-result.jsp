<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/form-result.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <title>BookRec Log-in</title>
</head>
<body>
	<header>
    	<c:import url="navbar.jsp"/>
    </header>
    <div class=mainbody>
    	<c:choose>
        	<c:when test="${message.error}">
        		<h1>Something went wrong</h1>
        	</c:when>
        	<c:otherwise>
        		<h1>Welcome back</h1>
        	</c:otherwise>
    	</c:choose>
    	<hr/>
    	<!-- display the message -->
		<c:import url="include/show-message.jsp"/>
		</hr>
		<c:if test="${not message.error}">
        	<p>Welcome back <c:out value="${user.name}"/>!</p>
    	</c:if>
    	<button id="theme">🌙</button>
	</div>
	<script src="js/theme8.js"></script>
</body>
</html>

