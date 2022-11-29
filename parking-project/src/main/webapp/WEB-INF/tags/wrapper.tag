<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title><c:out value="${pageTitle}" /></title>

<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<script src="js/helpers.js"></script>
</head>


<nav class="light-blue lighten-1" role="navigation">
	<div class="nav-wrapper container">
		<a href="#" class="brand-logo">Parking</a>
		<ul class="right">
			<li><a href="/place">Places</a></li>
			<li><a href="/car">Cars</a></li>
			<li><a href="/model">Models</a></li>
      <li><a href="/user">Users</a></li>
      <li><a href="/brand">Brands</a></li>
      <li><a href="/car2place">Cars to places</a></li>
		</ul>
	</div>
</nav>


<div class="section no-pad-bot" id="index-banner">
	<div class="container">
		<jsp:doBody />
		<!-- Page body will be here -->
	</div>
</div>
<footer class="light-blue page-footer">
          <div class="container">
            <div class="row">
              <div class="col l6 s12">
                <h5 class="white-text">Parking in your city</h5>
                <p class="grey-text text-lighten-4">Our address is Miami Beach</p>
              </div>
              <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                  <li><a class="grey-text text-lighten-3" href="/place">Places</a></li>
			            <li><a class="grey-text text-lighten-3" href="/car">Cars</a></li>
			            <li><a class="grey-text text-lighten-3" href="/model">Models</a></li>
                  <li><a class="grey-text text-lighten-3" href="/user">Users</a></li>
                 <li><a class="grey-text text-lighten-3" href="/brand">Brands</a></li>
                  <li><a class="grey-text text-lighten-3" href="/car2place">Cars to places</a></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="footer-copyright">
            <div class="container">
            © 2014 Copyright Text
            <a class="grey-text text-lighten-4 right" href="#!">More Links</a>
            </div>
          </div>
        </footer>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>