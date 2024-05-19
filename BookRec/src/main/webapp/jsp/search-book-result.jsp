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
		<title>BookRec Search</title>
		<link rel="stylesheet" type="text/css" href="css/style1.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                <table>
                    <tr>
                        <td rowspan="2"><img><c:out value="${book.cover}"/><img></td>
                        <td colspan="3">
                            <b><c:out value="${book.title}"/></b> :<br>
                            <c:out value="${book.plot}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><c:out value="${book.release}"/></td>
                        <td><c:out value="${book.isbn}"/></td>
                        <td>
                            <c:out value="${book.publisher_name}"/>
                        </td>
                    </tr>
                </table>
                </hr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
        <button id="theme-toggle">Toggle Theme</button>
        <script src="js/theme.js"></script>
	</body>
</html>
