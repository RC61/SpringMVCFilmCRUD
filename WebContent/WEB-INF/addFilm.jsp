  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Film</title>
</head>
<body>
	<h2>Add a Film</h2>
	<br>
	<br> Enter a film and it's details:
	<br>
	<form action="createFilm.do" method="POST">
		
		Title:<input type="text" name="title" value="${film.title }"/>
		<br>
		Description:<input type="text" name="description" size="4"/> 
		<br>
		Rating:<input type="text" name="rating" size="4"/> 
		<br>
		Release Year:<input type="text" name="releaseYear" size="4"/> 
		<br>
		<label>For the following, please type (1) for English, (2) for Italian, (3) for Japanese, (4) for Mandarin, (5) for French, or (6) for German</label>
		Language ID:<input type="text" name="language" size="4"/>
		<br>
		<input type="submit" value="Submit" />
	</form>
</body>
</html>