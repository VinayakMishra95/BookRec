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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../css/home.css">
    <link rel="stylesheet" type="text/css" href="../css/navbar.css">
    <title>BookRec</title>
<<<<<<< HEAD
    <style>
        /* Add some basic styling for the logout button */
        .user-related {
            position: absolute;
            top: 10px;
            right: 10px;
            display: flex;
            align-items: center;
        }

        .user-related .username {
            margin-right: 10px;
            font-weight: bold;
            font-size: 16px;
        }

        .user-related form input[type="submit"] {
            background-color: green;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }

        .user-related form input[type="submit"]:hover {
            background-color: darkgreen;
        }
    </style>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
=======
>>>>>>> borto
</head>
<body>
    <header>
    	<c:import url="navbar.jsp"/>
    </header>
	<div class="mainbody">
	<h1>BookRec Homepage</h1>
    <div class="site-navigation">
        <form action="search-book-form.jsp" method="get">
        <button type="submit">Search for books</button>
        </form>
        <form action="search-user-form.jsp" method="get">
        <button type="submit">Search for users</button>
        </form>
        <form action="search-author-form.jsp" method="get">
        <button type="submit">Search for authors</button>
        </form>
    </div>
    </div>
    <script src="../js/dropdown.js"></script>
</body>
</html>
