<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${singleFacility.name}</title>
</head>
<body>


<h1><c:out value="${singleFacility.name}"/></h1>
<h2><c:out value="${singleFacility.description}"/></h2>


</body>
</html>