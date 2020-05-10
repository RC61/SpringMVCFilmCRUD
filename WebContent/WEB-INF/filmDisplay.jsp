<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film</title>
</head>
<body>
	<c:choose>
	<c:when test="${! empty film }">
		<ul>
		<li>ID: ${film.title }</li>
		
		<li>Name: ${film.description }</li>
		<li>Rating: ${film.rating }</li>
		
		
		</ul>
		
		
		
		
	</c:when>
	<c:otherwise>
	<p>No film found</p>
	
	</c:otherwise>
	</c:choose>



</body>
</html>