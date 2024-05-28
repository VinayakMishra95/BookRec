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
    <title>BookRec Search</title>
    <link rel="stylesheet" type="text/css" href="css/book-result.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/x-icon" href="images/favicon.ico">
</head>

<body>
    <h1>Search Books</h1>
    
    <!-- display the message -->
    <c:import url="include/show-message.jsp"/>
    
    <!-- display the list of found authors, if any -->
    <c:if test='${empty bookList}'>
        <h2>No books found :(</h2>
    </c:if>
    <c:if test='${not empty bookList}'>
        <c:forEach var="book" items="${bookList}">
            <div class="book-item">
                <div class="book-cover">
                    <img width="50" height="50" src="<c:url value='/load-img'><c:param name='book-cover' value='${book.isbn}'/></c:url>"/>
                </div>
                <div class="book-details">
                    <h3><c:out value='${book.title}'/></h3>
                    <p><c:out value='${book.plot}'/></p>
                    <ul>
                        <li>Released in: <c:out value='${book.release}'/></li>
                        <li>ISBN: <c:out value='${book.isbn}'/></li>
                        <li>By: <c:out value='${book.publisher_name}'/></li>
                    </ul>
                </div>
            </div>
            <hr>
        </c:forEach>
    </c:if>
    <button id="theme-toggle">Toggle Theme</button>
    <script src="js/theme.js"></script>
</body>
</html>

