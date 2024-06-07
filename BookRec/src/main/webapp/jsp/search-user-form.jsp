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
    <meta charset="utf-8">
    <title>User search</title>
    <link rel="stylesheet" type="text/css" href="../css/form.css">
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
</head>
<body>
	<header>
    	<c:import url="navbar.jsp"/>
	</header>
	<div class="mainbody">
		<h1>Search for an user:</h1>
		<form method="POST" action="<c:url value="/user"/>">
    		<label for="nameID">Name:</label>
    		<input id="nameID" name="name" type="text"/><br/><br/>
    		<button type="submit">Submit</button><br/>
    		<button type="reset">Reset the form</button>
		</form>
		<button id="theme">🌙</button>
	</div>
	<script src="../js/theme8.js"></script>
</body>
</html>
