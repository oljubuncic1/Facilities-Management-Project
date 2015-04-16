<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<title>Upload facilities</title>
</head>
<body>

<h1>Upload facilities</h1>

<br> <br> <br> <br> <br>

<form:form method="post" action="upload">


<label>Choose the data file:</label>
<input type="file"> <br> <br> <br>
<label>Choose the configuration: </label>
<select id="conf_select" class="selement listbox"></select> 
<input type="button" value="Add new configuration" class="styled-button-8"> <br> <br> <br>
<input type="button" value="Upload" class="styled-button-8">
</form:form>
</body>
</html>