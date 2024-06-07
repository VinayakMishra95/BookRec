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
