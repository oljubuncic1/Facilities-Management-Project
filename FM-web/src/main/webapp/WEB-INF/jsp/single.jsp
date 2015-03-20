<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<title>${singleFacility.name}</title>
</head>
<body>


<h1><c:out value="${singleFacility.name}"/></h1>

<div id="main_input">
<div class="image">
<img src="http://grfx.cstv.com/schools/ksu/graphics/training-facility/bg-home4.jpg" width="300" height="300">
</div>

<div class="info">
<p>
<label><i>Address:</i></label> <br>
<c:out value="${singleFacility.address}"/> <br>
<c:out value="${singleFacility.city}"/> <br>
<label><i>Category:</i></label> <br>
<c:out value="${singleFacility.category}"/> <br>

<label><i>Contact information:</i></label> <br>
<c:out value="${singleFacility.phone}"/> <br>
<c:out value="${singleFacility.email}"/> <br>
<c:out value="${singleFacility.website}"/> <br>
</p>
</div>

<div style="float:right">
			<input type="button" class="styled-button-8"  onclick="location.href='${pageContext.request.contextPath}/facility/update?fac_id=${singleFacility.id }'" value="Change" value="Change">
			<input type="button" class="styled-button-8" onclick="location.href='${pageContext.request.contextPath}/facility/delete?fac_id=${singleFacility.id }'" value="Delete">
			</div>
</div>

<div class="desc">
<c:out value="${singleFacility.description}"/>
</div>

<div class="back">
<a href ="${pageContext.request.contextPath}/facility/">Back to facilities list</a>
</div>



</body>
</html>