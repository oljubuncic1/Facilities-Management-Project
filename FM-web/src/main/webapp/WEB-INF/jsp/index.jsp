<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">

<title>Current facilities</title>
</head>
<body>

<h1>Current facilities</h1>







<c:forEach var="fac" items="${facilitiesList }">


<div class="single">
<div class="smallImage"><img src="http://grfx.cstv.com/schools/ksu/graphics/training-facility/bg-home4.jpg" width="200" height="200"></div>
<div class="smallInfo">
<h2>${fac.name }</h2>
<label>${fac.city }</label> <br>



</div>

<div class="buttonDiv" style="float:right">
<input type="button" class="styled-button-8" onclick="location.href='${pageContext.request.contextPath}/facility/single?fac_id=${fac.id }'" value="Details">
</div>

</div>
<div class="separator"></div>
</c:forEach>



<br>
<div class="butDiv">
<input type="button" onclick="location.href='${pageContext.request.contextPath}/facility/addForm'" class="styled-button-8" value="Add new facility" style="width:100%">
</div>
</body>
</html>