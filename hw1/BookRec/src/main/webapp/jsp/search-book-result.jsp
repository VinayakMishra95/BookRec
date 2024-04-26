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
	</head>

	<body>
		<h1>Search Books</h1>
		
		<!-- display the message -->

		<!-- display the list of found authors, if any -->
		<c:if test='${not empty authorList}'>
			<table>
				<thead>
					<tr>
						<th>ISBN</th><th>Title</th><th>Plot</th><th>Release</th><th>Publisher</th><th></th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="book" items="${bookList}">
						<tr>
						<td><c:out value="${book.isbn}"/></td>
						<td><c:out value="${book.title}"/></td>
						<td><c:out value="${book.plot}"/></td>
						<td><c:out value="${book.release}"/></td>
						<td><c:out value="${book.publisher}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test='${empty bookList}'>
		    <h2>0 books found :(</h2>
		</c:if>
	</body>
</html>
