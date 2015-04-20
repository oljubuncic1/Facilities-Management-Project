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
<body onload="displayCount();">

<h1>Upload facilities</h1>

<br> <br> <br> <br> <br>


<script>

	function displayCount()
	{
		var c = ${numberUploaded};
		var d = document.getElementById('numberAdded');
		
		if( c != NaN)
			{
		
				alert("notnan");
				d.getElementsByTagName('p')[0].innerHTML = "You have successfully added " +  ${numberUploaded} + " facilities.";
			}
		else
			{
			alert("nan");
			d.getElementsByTagName('p')[0].innerHTML = "";
			}
		
		
	}
	

	

	
	
</script>

<form method="post" action="upload" enctype="multipart/form-data">


<label>Choose the data file:</label>
<input type="file" name="file" /> <br> <br> <br>
<label>Choose the configuration: </label>
<select id="conf_select" class="selement listbox" name="cName">
<c:forEach var="con" items="${configurations}">
<option value= "${con.name }" >${con.name }</option>
</c:forEach>

</select> 
<a href ="${pageContext.request.contextPath}/facility/addConfForm">Add new configuration</a> <br> <br> <br>
<input type="submit" value="Upload" class="styled-button-8">
</form>

<div id="numberAdded">
<p></p>
</div>

<div class="back">
<a href ="${pageContext.request.contextPath}/facility/">Back to facilities list</a>
</div>

</body>
</html>