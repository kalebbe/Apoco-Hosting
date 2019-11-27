<!-- 
   - Author:		  Kaleb Eberhart
   - Date:            09/23/18
   - Course:          CST-341
   - Project Name:    Apoco
   - Project Version: 1.0
   - Module Name:     defaultTemplate.jsp
   - Module Version:  1.0
   - Summary:         This is the default template for my project. This template will most likely be used
   -				   for everything and the header/footer will be changed to reflect other pages.
 -->

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Apoco</title>
<!-- Assets created with Bootstrap Studios -->
<link href="<c:url value="/assets/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/assets/fonts/simple-line-icons.min.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Abril+Fatface">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Aldrich">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Alegreya+SC">
<link href="<c:url value="/assets/css/Footer-Basic.css" />"
	rel="stylesheet">
<link href="<c:url value="/assets/css/Login-Form-Clean.css" />"
	rel="stylesheet">
<link href="<c:url value="/assets/css/Navigation-Clean.css" />"
	rel="stylesheet">
<link href="<c:url value="/assets/css/Navigation-with-Button.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/assets/css/Registration-Form-with-Photo.css" />"
	rel="stylesheet">
<link href="<c:url value="/assets/css/Team-Boxed.css" />"
	rel="stylesheet">
<link href="<c:url value="/assets/css/styles.css" />" rel="stylesheet">
<script src="<c:url value="/assets/js/jquery.min.js" />"></script>
<script src="<c:url value="/assets/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/assets/js/wordCount.js" />"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>

<body>
	<div id="content" style="background-color:#484848">
		<!-- Header -->
		<tiles:insertAttribute name="header" />

		<!-- Body Page -->
		<div style="min-height: 74vh;">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- Footer Page -->
		<tiles:insertAttribute name="footer" />
	</div>
</body>

</html>