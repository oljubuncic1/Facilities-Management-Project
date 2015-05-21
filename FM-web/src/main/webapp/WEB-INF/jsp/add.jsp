<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Add new facility</title>
</head>
<body>


	<script>
	
	
	
		function addAddress()
		{
			var s = '</div><div class="label">\
				<label style="color:rgb(230,230,230)">Add new address</label>\
				</div><div class="label">\
			<label>Street: *</label>\
			</div>\
			<div class="label">\
				<label>House number: *</label>\
			</div>\
			<div class="label">\
				<label>City: *</label>\
			</div> <br>\
			<div class="label">\
				<input name="street" required/>\
			</div>\
			<div class="label">\
				<input name="number" required/>\
			</div>\
			<div class="label">\
				<input name="city" required/>\
			</div> <br>\
			<div class="label">\
				<label>Postal code: *</label>\
			</div>\
			<div class="label">\
				<label>Country: *</label>\
			</div>\
			<div class="label"><label style="color:rgb(230,230,230)">Add new address</label></div>\
			<div class="label">\
				<input name="postal_code" required/>\
			</div>\
			<div class="label">\
				<input name="country" required/>\
			</div><div class="label">\
			<label style="color:rgb(230,230,230)">Add new address</label>\
			</div>  <br>'; 
			
			//alert(s);
			//var d = document.getElementById("address_div");
			//d.innerHTML += s;
			
			
			$("#address_div").append(s);
			
		}
		
		function addPhone()
		{
			var s = '<label>Phone: *</label> <br>\
			<input name="phone" required/> <br>';
			
			$("#phone_div").append(s);
		}
		
		function addCat()
		{
			var s = '<label>Category: *</label> <br>\
			<input name="category" required/> <br>';
			
			$("#cat_div").append(s);
		}
		
		function addEmail()
		{
			var s = '<label>Email: *</label> <br>\
			<input name="email" required/> <br>';
			
			$("#email_div").append(s);
		}
		
		
	
	</script>

	<h1>Add new facility</h1>
	
	

	<form method="post" action="add">
		<div id="main_input">
			<h2 id="input">Facility information</h2>

			<p>
				<i>Fields marked with * are mandatory</i>
			</p>


			<label>Name: *</label> <br> <input name="name" required/> <br>
			<label>Website: </label> <br> <input name="website" /> <br>
			

			<div id="address_div">
			<p>Address data: </p>
				<div class="label">
					<label>Street: *</label>
				</div>
				<div class="label">
					<label>House number: *</label>
				</div>
				<div class="label">
					<label>City: *</label>
				</div> <br>
				<div class="label">
					<input name="street" required/>
				</div>
				<div class="label">
					<input name="number" required/>
				</div>
				<div class="label">
					<input name="city" required/>
				</div> <br>
				
				<div class="label">
					<label>Postal code: *</label>
				</div>
				<div class="label">
					<label>Country: *</label>
				</div>
				
				<div class="label"><label style="color:rgb(230,230,230)">Add new address</label></div>
				
				<div class="label">
					<input name="postal_code" required/>
				</div>
				<div class="label">
					<input name="country" required/>
				</div> 
				<div class="label">
					<label style="color:rgb(230,230,230)">Add new address</label>
				</div>
				 
			</div> <br> <br>
			
			<input type="button" value="+" onclick="addAddress();"/>
				
				
				 <br> <br> 
			 


			
			<div id="phone_div">
			
			
				<label>Phone: *</label> <br>
			
			
			
				<input name="phone" required/> <br>
			
			
			
			</div>
			
			<input type="button" value="+" onclick="addPhone();"/> <br>
			
			<div id="email_div">
			
			
				<label>Email: *</label> <br>
			
			
			
				<input name="email" required/> <br>
			
			
			
			</div>
			
			<input type="button" value="+" onclick="addEmail();"/> <br>
			
			<div id="cat_div">
			
			
				<label>Category: *</label> <br>
			
			
			
				<input name="category" required/> <br>
			
			
			
			</div>
			
			<input type="button" value="+" onclick="addCat();"/> <br>
			
			
			
			
			
			<br> <label>Description: *</label> <br>
			<textarea name="description" cols="30" rows="5" required></textarea>

			<div class="buttonsadd">
				<input type="reset" class="styled-button-8" value="Reset"> <input
					type="submit" class="styled-button-8" value="Save">
			</div>

		</div>
	</form>

</body>
</html>