<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<style>
#map-canvas {
	height: 40%;
	width: 30%;
	top: 20%;
	left: 65%;
	position: absolute;
}

.map_address, .ver
{
	font-family:Verdana;
	
}

.desc {
	position: relative;
	left: 10%;
	top: 70%;
	font-family: "Verdana";
}

#main_input {
	width: 50%;
	position: absolute;
	top: 20%;
	left: 10%;
}

.back {
	position: relative;
	top: 450px;
}

html, body {
	height: 100%;
	margin: 0px;
	padding: 0px;
}

#panel {
	position: absolute;
	top: 5px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}
</style>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
<script>
	var geocoder;
	var map;
	function initialize() {
		geocoder = new google.maps.Geocoder();
		var latlng = new google.maps.LatLng(43.85, 18.25);
		var mapOptions = {
			zoom : 13,
			center : latlng
		}
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
		showAddresses();
	}

	function codeAddress(address) {
		
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location
				});
			} else {
				alert('Geocode was not successful for the following reason: '
						+ status);
			}
		});
	}

	function showAddresses()
	{
		for(var i=0; i < document.getElementsByClassName("map_address").length;i++)
			{
				
				codeAddress(document.getElementsByClassName("map_address")[i].innerHTML);
			}
		
		
		
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>

	<h1>
		<c:out value="${singleFacility.name}" />
	</h1>

	<div id="main_input">
		<div class="image">
			<img
				src="http://grfx.cstv.com/schools/ksu/graphics/training-facility/bg-home4.jpg"
				width="300" height="300">
		</div>

		<div class="info">
			<p>
				<label><i>Addresses:</i></label> <br>
				<c:forEach var="addr" items="${singleFacility.addresses }">
				<div class='map_address'>
					<c:out
						value="${addr.street} ${addr.houseNumber }, ${addr.city.name }"/>
						</div>
						<div class='ver'>
						<c:out value="${addr.city.country.name }" />
						</div>
					
				</c:forEach>
				<label><i>Categories:</i></label> <br>
				<c:forEach var="cat" items="${singleFacility.categories }">
				<div class='ver'>
					<c:out value="${cat.name }" />
					</div>
				</c:forEach>
				<br> <label><i>Contact information:</i></label> <br>
				<c:forEach var="phone" items="${singleFacility.phones }">
				<div class='ver'>
					<c:out value="${phone.number }" />
					</div>
				</c:forEach>
				
				<c:forEach var="mail" items="${singleFacility.emails }">
				<div class='ver'>
					<c:out value="${mail.address }" />
					</div>
				</c:forEach>
				
				<div class='ver'>
				<c:out value="${singleFacility.website}" />
				</div>
				
			</p>
		</div>

		<div style="float: right">
			<input type="button" class="styled-button-8"
				onclick="location.href='${pageContext.request.contextPath}/facility/update?fac_id=${singleFacility.id }'"
				value="Change" value="Change"> <input type="button"
				class="styled-button-8"
				onclick="location.href='${pageContext.request.contextPath}/facility/delete?fac_id=${singleFacility.id }'"
				value="Delete">
		</div>
	</div>

	<div class="desc">
		<c:out value="${singleFacility.description}" />
	</div>


	
	<div id="map-canvas"></div>

	<div class="back">
		<a href="${pageContext.request.contextPath}/facility/">Back to
			facilities list</a>
	</div>

</body>
</html>