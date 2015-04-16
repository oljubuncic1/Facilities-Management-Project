<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<title>Add new configuration</title>
</head>
<body>


<h1>Add new configuration</h1>

	<form:form method="post" commandName="Configuration" action="addConf">
		
			<div id="main_input" width="100px">
			<h2 id="input">
			Configuration details
			</h2>
			
		
			<label>Configuration name: </label> <br>
			<input> <br>
			
			<label>File type: </label>
			
			<input id="r1" type="radio" name="conf" onclick="toggle()" checked="checked">
			<label for="r1">CSV</label> 
			<input id="r2" type="radio" name="conf" onclick="toggle()">
			<label for="r2">XML</label> 
			<input id="r3" type="radio" name="conf" onclick="toggle()">
			<label for="r3">JSON</label> <br> <br> <br>
			
			
			<script>
				function toggle()
				{
					var r1 = document.getElementById('r1');
					var r2 = document.getElementById('r2');
					var r3 = document.getElementById('r3');
					
					if(r1.checked)
						{
						document.getElementById('csv_cont').style.display = 'block';
						}
					else document.getElementById('csv_cont').style.display = 'none';
					
				}
			
			</script>
			
			
			<div id="csv_cont" style="display:block">
			<label>Name: </label> <br>
			<form:input path="data" /> <br> 
			<div class="label"><label>Address: </label></div>
			<div class="label"><label>City: </label></div>
			<div class="label"><label>Postal code: </label></div> <br>
			<div class="label"><input /></div>
			<div class="label"><input /></div>
			<div class="label"><input /></div> <br>
			<div class="label"><label>Phone: </label></div>
			<div class="label"><label>Email:</label></div>
			<div class="label"><label>Website:</label></div> <br>
			<div class="label"><input /></div>
			<div class="label"><input /></div>
			<div class="label"><input /></div> <br>
			<label>Category: </label> <br>
			<input /> <br>
			<label>Description: </label> <br>
			<input  />
			
			</div>
			
			<div class="buttons">
			<input type="reset" class="styled-button-8" value="Reset">
			<input type="submit" class="styled-button-8" value="Save">
			</div>
			
			</div>
			
			
			
		
		
	</form:form>



</body>
</html>