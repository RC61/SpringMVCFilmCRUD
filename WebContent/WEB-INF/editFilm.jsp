<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>

	<div class="container formbg">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview alert alert-light">
					<form action="insertEdit.do" method="POST">
						title<input name="title" value="${film.title}" /> 
						<br/>
						<label for="description"><strong>Description</strong></label>
						<input name="description" value="${film.description}" /> 	
						</br>		
						<label for="rating">Choose Rating</label>
							<select id="rating" name="rating">
								<option value="G">G</option>
								<option value="PG">PG</option>
								<option value="PG13">PG13</option>
								<option value="R">R</option>
								<option value="NC17">NC-17</option>
							</select></br>
							<label for="releaseYear">Release Year</label>
							<input id="releaseYear" name="releaseYear" value="${film.releaseYear}"></intput>
							
							</br>
							<label for="language">Choose Language</label>
							<select id="languages" name="language">
								<option value="1">English</option>
								<option value="2">Italian</option>
								<option value="3">Japanese</option>
								<option value="4">Mandarin</option>
								<option value="5">French</option>
								<option value="6">German</option>
							</select>
							<br/>
						<button type="submit" value="SUMBIT">Submit</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	</select>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>
