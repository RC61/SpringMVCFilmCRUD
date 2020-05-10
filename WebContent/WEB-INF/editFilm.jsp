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
<H1>This is where you edit the film</H1>
	<form action="insertEdit.do" method="POST">
		title<input name="title"/>
		desc<input name="description"/>
		rating<input name="rating"/>
		release year<input name="releaseYear"/>
		language<input name="language"/>
		category<input name="category"/>
	</form>
</body>
</html>