<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update existing facility</title>
</head>
<body>

	<form:form method="post" commandName="Facility" action="updatePost">
		<table border="1" cellpadding="2" cellspacing="2">
			<tr>
				<td>ID</td>
				<td><form:input path="id" value="${facility.id }"/></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><form:input path="name" value="${facility.name }"/></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><form:textarea path="description" cols="20" rows="5" value="${facility.description }"></form:textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Save"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>