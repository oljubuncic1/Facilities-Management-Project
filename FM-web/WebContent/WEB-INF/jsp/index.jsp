<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facilities list</title>
</head>
<body>



<table border="1" cellpadding="2" cellspacing ="2">

<tr>
<th>ID</th>
<th>Name</th>
<th>Description</th>
</tr>

<c:forEach var="fac" items="${facilitiesList }">
<tr>
<td>${fac.id }</td>
<td><a href="${pageContext.request.contextPath}/facility/single?fac_id=${fac.id}">${fac.name }</a></td>
<td>${fac.description }</td>
<td><input type="button"  onclick="location.href='${pageContext.request.contextPath}/facility/delete?fac_id=${fac.id }'" value="Delete" ></td>
</tr>
</c:forEach>


</table>
<br>
<a href="${pageContext.request.contextPath}/facility/add">Add new facility</a>

</body>
</html>