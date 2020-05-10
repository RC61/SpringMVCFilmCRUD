<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" text="text/css" href="style.css">
<meta charset="UTF-8">
<title>Film List</title>
</head>
<body>
  
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto">
				<div class="post-preview">

					<ul>
						<c:forEach var="s" items="${film}">
							<li>
								<div class="alert alert-light">
									<h3 class="post-preview">${s.title}</h3>
									<hr>
									<p class="post-subtitle">
										<strong>DESCRIPTION: </strong>${s.description}</p>
									<p class="post-subtitle">
										<strong>RATING: </strong>${s.rating}</p>
									<p class="post-subtitle">
										<strong>RATING: </strong>${s.category}</p>
									<p class="post-subtitle">
										<strong>RELEASE YEAR: </strong>${s.releaseYear}</p>
									<p class="post-subtitle">
										<strong>CAST: </strong>${s.cast}</p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
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