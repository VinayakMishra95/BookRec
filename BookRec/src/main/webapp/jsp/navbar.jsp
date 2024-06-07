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
    
<nav class="navbar">
	<a class="navbar-brand" href="<c:url value="/jsp/home.jsp"/>">Homepage</a>
	<div class="navbar-navig" id="navbarNav">
		<c:choose>
       		<c:when test="${empty sessionScope.username}">
           		<ul>
					<li class="navig-item">
                   		<a class="navig-link" href="<c:url value="/jsp/login.jsp"/>">Login</a>
               		</li>
               		<li class="navig-item">
                   		<a class="navig-link" href="<c:url value="/jsp/signup.jsp"/>">Register</a>
               		</li>
           		</ul>
           	</c:when>
           	<c:otherwise>
           		<ul>
               		<li class="navig-item">
						<a class="navig-link" href="#" onclick="LogoutUser('<c:url value="/logout"/>'); return false;">Logout (<c:out value='${sessionScope.username}'/>)</a>
               		</li>
               		<li class="navig-item">
                   		<a class="navig-link" href="<c:url value="/jsp/delete-user.jsp"/>">Delete account</a>
               		</li>
          		</ul>
			</c:otherwise>
		</c:choose>
	</div>
</nav>
<script>
function LogoutUser(subURL) {
	// Create form to send to LogoutServlet
	var form = document.createElement("form");
	form.method = "POST";
	form.action = subURL;
	// Adds the form to the document
	document.body.appendChild(form);
	// Submit form
	form.submit();
}
</script>
