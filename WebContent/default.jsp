<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Apoco</title>
	<link href="<c:url value="/assets/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/assets/fonts/simple-line-icons.min.css"/>" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abril+Fatface">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Aldrich">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Alegreya+SC">
	<link href="<c:url value="/assets/css/Footer-Basic.css" />" rel="stylesheet">
	<link href="<c:url value="/assets/css/Login-Form-Clean.css" />" rel="stylesheet">
	<link href="<c:url value="/assets/css/Navigation-Clean.css" />" rel="stylesheet">
	<link href="<c:url value="/assets/css/Navigation-with-Button.css" />" rel="stylesheet">
	<link href="<c:url value="/assets/css/Registration-Form-with-Photo.css" />" rel="stylesheet">
	<link href="<c:url value="/assets/css/styles.css" />" rel="stylesheet">
	<script src="<c:url value="/assets/js/jquery.min.js" />"></script>
	<script src="<c:url value="/assets/bootstrap/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div style="background-color: #ff0000;">
		<nav class="navbar navbar-light navbar-expand-md navigation-clean"
			style="background-color: #660000;">
			<div class="container">
				<a class="navbar-brand" href="#"
					style="color: #ffffff; font-size: 30px;">Apoco</a>
				<button class="navbar-toggler" data-toggle="collapse"
					data-target="#navcol-1">
					<span class="sr-only">Toggle navigation</span><span
						class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navcol-1">
					<ul class="nav navbar-nav ml-auto">
						<c:choose>
							<c:when test="${sessionScope.username != null}">
								<li class="nav-item" role="presentation"><a
									class="nav-link nav-btn" href="social/social"
									style="color: #005024; font-family: 'Alegreya SC', serif;">Social</a></li>
								<li class="nav-item" role="presentation"><a
									class="nav-link nav-btn" href="business/bus"
									style="color: #09136e; font-family: Aldrich, sans-serif;">Business</a></li>
								<li class="nav-item" role="presentation"><a
									class="nav-link nav-btn" href="dating/dat"
									style="color: #dd00b9; font-family: fantasy;">Dating</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item" role="presentation"><a
									class="nav-link nav-btn" href="login/redirect"
									style="color: #005024; font-family: 'Alegreya SC', serif;">Social</a></li>
								<li class="nav-item" role="presentation"><a
									class="nav-link nav-btn" href="login/redirect"
									style="color: #09136e; font-family: Aldrich, sans-serif;">Business</a></li>
								<li class="nav-item" role="presentation"><a
									class="nav-link nav-btn" href="login/redirect"
									style="color: #dd00b9; font-family: fantasy;">Dating</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div>
		<nav
			class="navbar navbar-light navbar-expand-md navigation-clean-button"
			style="background-color: #000000; height: 50px;">
			<div class="container">
				
				<button class="navbar-toggler" data-toggle="collapse"
					data-target="#navcol-2">
					<span class="sr-only">Toggle navigation</span><span
						class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navcol-2">
					<ul class="nav navbar-nav mr-auto"></ul>
					<span class="navbar-text actions"> <a href="login/log"
						class="login" style="color: #ffffff; font-size: 14px;">Log In</a><a
						class="btn btn-light action-button" role="button"
						href="registration/reg"
						style="background-color: #660000;; color: #ffffff; font-size: 14px;">Sign
							Up</a></span>
				</div>
			</div>
		</nav>
	</div>
</body>
<div align="center" style="min-height: 74vh; background-color:#484848">
	<br /> <img src="<c:url value="/assets/img/Apoco.png" /> ">
</div>
<div class="footer-basic" style="background-color: #660000;">
	<footer>
		<p class="copyright" style="color: #ffffff;">Apoco Inc © 2018-2020</p>
	</footer>
</div>
