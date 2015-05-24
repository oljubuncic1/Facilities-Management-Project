<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="myControllerVar" value="/facility" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<title>Current facilities</title>
</head>
<body>


	<script>
	
	function showCities()
	{
			var country = document.getElementById('con').value;
			
			if(country == 'Any')
				{
				
				document.getElementById('cityDiv').style.display = 'none';
				}
			
			else
			$.ajax({
				url : "${pageContext.request.contextPath}/facility/country/" + country,
				type : "GET",
				 beforeSend: function(xhr) {
			            xhr.setRequestHeader("Accept", "application/json");
			            xhr.setRequestHeader("Content-Type", "application/json");
			        },
 
				success : function(response) {
					
					
					
					var city = document.getElementById('cit');
					$("#cit").empty();
					var any = document.createElement("option");
					any.text = "Any";
					any.value="Any";
					city.add(any);
					
					for(var i=0;i<response.length;i++)
						{
							var option = document.createElement("option");
							option.text = response[i];
							option.value = response[i];
							city.add(option);
						
						
						}
					
					
					document.getElementById('cityDiv').style.display = 'block';
					
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
		
	}
	</script>

	<h1>Current facilities</h1>

	<div id="search_box">
		<input type="text" maxlength="50" id="search_text"
			placeholder="Search for facilities...">
		<div id="search_icon_cont">
			<input type="button" id="searchButton"
				onclick="location.href='${pageContext.request.contextPath}/facility/search/' + document.getElementById('search_text').value;"
				class="styled-button-8" value="Search" >
		</div>
	</div>




	<div id="prod_search">
		<h2>Find facilities</h2>


		
			<div id="search_form" class="container">

				<form method="post" action = "${pageContext.request.contextPath}/facility/advancedSearch">


				<div id="l_search" class="cdiv">
					<p class="slabel">Name:</p>
					<input type="text" class="selement textbox" size="30" name="facName"> <br>


					<p class=" slabel">Category:</p>
					<select class="selement listbox" name="catName">
						<option value="Any" selected="selected">Any</option>
						<c:forEach var="categ" items="${categoriesList }">
						<option value="${categ.name }">${categ.name }</option>
						</c:forEach>
					</select> <br>

					<p class=" slabel">Address:</p>
					<input type="text" class="selement textbox" size="30" name="addrName"> <br>

					<p class=" slabel">Street number:</p>
					<input type="text" class="selement numeric" size="30" name="num"> <br>


					<p class=" slabel">Country:</p>
					
					
					<select id="con" class="selement listbox" onchange="showCities()" name="countryName">
					
						<option value="Any">Any</option>
						<c:forEach var="c" items="${countriesList }">
						<option value="${c.name }">${c.name }</option>
						</c:forEach>
					</select>
					 <br>
					
					<div id="cityDiv" style="display:none">
					<p class=" slabel">City:</p>
					<select id="cit" class="selement listbox" name="cityName">
						<option value="Any">Any</option>
					</select></div> <br>


				</div>

				<div id="s_buttons">


					<input type="submit" value="Search" class="styled-button-8">

				</div>
				
				</form>

			</div>

		

	</div>




	<c:forEach var="fac" items="${facilitiesList }">


		<div class="single">
			<div class="smallImage">
				<img
					src=${pageContext.request.contextPath}/facility/image/${fac.id}
					width="200" height="200">
			</div>
			<div class="smallInfo">
				<h2>${fac.name }</h2>
				<label>${fac.description }</label> <br>



			</div>

			<div class="buttonDiv" style="float: right">
				<input type="button" class="styled-button-8"
					onclick="location.href='${pageContext.request.contextPath}/facility/single?fac_id=${fac.id }'"
					value="Details">
			</div>

		</div>
		<div class="separator"></div>
	</c:forEach>



	<br>
	<div class="butDiv">
		<input type="button"
			onclick="location.href='${pageContext.request.contextPath}/facility/addForm'"
			class="styled-button-8" value="Add new facility"
			style="width: 45% float:left"> <input type="button"
			onclick="location.href='${pageContext.request.contextPath}/facility/uploadForm'"
			class="styled-button-8" value="Import facilities"
			style="width: 45% float:right">
	</div>
</body>
</html>