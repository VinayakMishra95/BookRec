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
    <title>BookRec Signin</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css">
</head>
<body>
    <!-- Signin Form -->
    <div class="form-container">
        <form action="<c:url value="/login"/>" method="POST">
            <h2>Sign in</h2>
            
            <label for="name">Username:</label>
            <input type="text" id="name" name="name" placeholder="Enter your username" required><br>
            <label for="new-password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter password" required><br>
            <input type="submit" value="Signin">
            <div>New user? <a href="signup.jsp">Signup</a></div>
        </form>
    </div>
</body>
</html>
