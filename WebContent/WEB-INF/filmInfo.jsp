<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<h1>Film Details</h1>
	<c:choose>
		<c:when test="${! empty film }">
			<ul>
				<li><strong>Film Title:</strong> ${film.title }</li>
				<li><strong>Description:</strong>${film.description}</li>
				<li><strong>Rating</strong>${film.rating}</li>
				<li><strong>release year</strong> ${film.releaseYear}</li>
			</ul>
	</c:when>
	</c:choose>

</body>
</html>