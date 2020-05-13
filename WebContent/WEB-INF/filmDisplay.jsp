
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
	<h1>FilmDisplay JSP</h1>
	<c:choose>
		<c:when test="${! empty film }">
			<ul>
				<li>Title: ${film.title }</li>

				<li>Name: ${film.description }</li>
				<li>Rating: ${film.rating }</li>
				<li>ID: ${film.ID}</li>


			</ul>



			<form action="editFilm.do" method="GET">
				<input type="hidden" name="id" value="${film.ID}">
				<button name="id" type="submit">Edit</button>
			</form>
		</c:when>
		<c:otherwise>
			<p>No film found</p>

		</c:otherwise>
	</c:choose>



</body>
</html>