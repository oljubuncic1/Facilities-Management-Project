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

<div id="search_box">
<input type="text"  maxlength="50" id="search_text" placeholder="Search for facilities...">
<div id="search_icon_cont">
<img src="/resources/search.png" alt="search" class="search_img" >
</div>
</div>


<div id="prod_search">
<h2>Find facilities</h2>
<form method="post" action="something.php">
<div id="search_form" class="container">




<div id="l_search" class="cdiv">
<p  class=" slabel">Category: </p>
<select class="selement listbox">
<option>All</option>
<option>Food</option>
<option>Sports</option>
<option>Shopping</option>
<option>Bussiness</option>
<option>Entertainment</option>
<option>Miscellaneous</option>
</select>

<p  class=" slabel">City: </p> 
<input type="text" class="selement textbox" size="30" > <br>
<p  class=" slabel">Country: </p> 
<input type="text" class="selement textbox" size="30" >
<p class=" slabel">Date: </p> 
<select class="selement listbox">
<option>Anytime</option>
<option>This week</option>
<option>This month</option>
<option>This year</option>

</select>

</div>

<div id="s_buttons" >


<input type="submit" value="Search" class="styled-button-8">

</div>

</div>

</form>

</div>




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
<input type="button" onclick="location.href='${pageContext.request.contextPath}/facility/addForm'" class="styled-button-8" value="Add new facility" style="width:45% float:left">
<input type="button" onclick="location.href='${pageContext.request.contextPath}/facility/uploadForm'" class="styled-button-8" value="Import facilities" style="width:45% float:right">
</div>
</body>
</html>