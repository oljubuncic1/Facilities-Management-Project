<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<title>Add new facility</title>
</head>
<body>

<h1>Add new facility</h1>

	<form:form method="post" commandName="Facility" action="add">
		
			<div id="main_input" width="100px">
			<h2 id="input">
			Facility information
			</h2>
			
			<p><i>Fields marked with * are mandatory</i></p>
			
			
			<label>Name: *</label> <br>
			<form:input path="name"/> <br> 
			
			
			<div class="label"><label>Website:</label></div> <br>
		
			<div class="label"><form:input path="website"/></div> <br>
			<label>Id: *</label> <br>
			<form:input path="id"/> <br>
			<label>Description: *</label> <br>
			<form:textarea path="description" cols="30" rows="5"></form:textarea>
			
			<div class="buttons">
			<input type="reset" class="styled-button-8" value="Reset">
			<input type="submit" class="styled-button-8" value="Save">
			</div>
			
			</div>
			
			
			
		
		
	</form:form>

</body>
</html>