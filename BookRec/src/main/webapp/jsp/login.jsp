<!--
Copyright 2018-2023 University of Padua, Italy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Version: 1.0
Since: 1.0
-->

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/auth.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <title>BookRec Log-in</title>
</head>
<body>
	<header>
    	<c:import url="navbar.jsp"/>
    </header>
    <!-- Log-in Form -->
    <div class="mainbody">
    <div class="form-container">
        <form action="<c:url value="/login"/>" method="POST">
            <h2>BookRec Log-in</h2>
            <label for="name">Username:</label>
            <input type="text" id="name" name="name" placeholder="Enter your username" maxlength="50" required><br>
            <label for="new-password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter password" maxlength="200" required><br>
            <input type="submit" value="Log in">
            <div>New user? <a href="signup.jsp">Register</a></div>
        </form>
    </div>
    </div>
</body>
</html>
