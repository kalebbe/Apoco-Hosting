<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<div class="container">
	<div align="center">
	<h1><c:out value="${job.jobTitle}"/></h1>
	<br><hr>
	<c:choose>
		<c:when test="${sessionScope.message != null}">
			<p class="error">
				<c:out value="${sessionScope.message}" />
			</p>
			<c:remove var="message" />
		</c:when>
		<c:when test="${sessionScope.message1 != null}">
			<p>
				<c:out value="${sessionScope.message1}" />
			</p>
			<c:remove var="message1" />
		</c:when>	
	</c:choose>
	</div>
	<div class="row">
		<div class="col-md-3">
			<h3>
				<c:out value="${job.company}" />
			</h3>
			<img src="<c:url value="/assets/img/LocPlaceholder.png" />" style="width: 150px; height: 150px;"> <br>
			<p>
				<c:out value="${job.city}, ${job.state}" />
				<br> <strong>Salary:</strong>&nbsp
				<c:out value="${job.salary} yearly" />
				<br> <strong>Field:</strong>&nbsp
				<c:out value="${job.type}" />
				<br> <strong>Posted on:</strong>&nbsp
				<c:out value="${job.date}" />
			</p>
		</div>
		<div class="col-md-9">
			<div align="center">
				<h3>Job Description</h3>
			</div> <br>
			<p><c:out value="${job.description}" /></p>
			<br>
			<hr>
			<div align="center">
				<h3>Job Requirements</h3>
			</div> <br>
			<p style="white-space: pre;"><c:out value="${job.requirements}" /></p>
			<hr>
			<div align="center">
				<h3><a href="${job.link}" target="_blank">Apply Here</a></h3>
			</div>
		</div>
	</div>
</div>