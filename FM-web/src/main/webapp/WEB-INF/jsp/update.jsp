<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<title>Facility update</title>
</head>
<body>

<h1>Facility update</h1>

	<form:form method="post" commandName="Facility" modelAttribute="facility" action="updatePost">
		<div id="main_input" width="100px">
			<h2 id="input">
			Facility information
			</h2>
			
			<p><i>Fields marked with * are mandatory</i></p>
			
			
			<label>Name: *</label> <br>
			<form:input path="name" value="${facility.name }"/> <br> 
			<div class="label"><label>Address: *</label></div>
			<div class="label"><label>City: *</label></div>
			<div class="label"><label>Postal code: *</label></div> <br>
			<div class="label"><form:input path="address" value="${facility.address }"/></div>
			<div class="label"><form:input path="city" value="${facility.city }"/></div>
			<div class="label"><form:input path="postal_code" value="${facility.postal_code }"/></div> <br>
			<div class="label"><label>Phone: *</label></div>
			<div class="label"><label>Email:</label></div>
			<div class="label"><label>Website:</label></div> <br>
			<div class="label"><form:input path="phone" value="${facility.phone }"/></div>
			<div class="label"><form:input path="email" value="${facility.email }"/></div>
			<div class="label"><form:input path="website" value="${facility.website }"/></div> <br>
			<label>Category: *</label> <br>
			<form:input path="category" value="${facility.category }"/> <br>
			<label>Description: *</label> <br>
			<form:textarea path="description" cols="30" rows="5" ></form:textarea>
			
			<div class="buttons">
			<input type="reset" class="styled-button-8" value="Reset">
			<input type="submit" class="styled-button-8" value="Save">
			</div>
			
			</div>
	</form:form>

</body>
</html>