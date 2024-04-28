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
		<h1>Search User</h1>
		<hr>
		
		<!-- display the message -->
		<c:import url="include/show-message.jsp"/>
		
		<!-- display the list of found users, if any -->
		<c:if test='${empty userList}'>
		    <h2>No users found :(</h2>
		</c:if>
		<c:if test='${not empty userList}'>
			<table>
				<thead>
					<tr>
						<th>Username</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr>
						<td><c:out value="${user.name}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</body>
</html>
