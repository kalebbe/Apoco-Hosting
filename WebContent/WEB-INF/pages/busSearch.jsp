<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" align="center">
	<c:choose>
		<c:when test="${sessionScope.search.equals('both')}">
			<h1>Search Results</h1>
		</c:when>
		<c:otherwise>
			<h1>Job Listings</h1>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${sessionScope.message != null}">
			<p class="error">
				<c:out value="${sessionScope.message}" />
			</p>
			<c:remove var="message" />
		</c:when>
		<c:when test="${sessionScope.message2 != null}">
			<p>
				<c:out value="${sessionScope.message2}" />
			</p>
			<c:remove var="message2" />
		</c:when>
	</c:choose>
	<br>
	<c:forEach var="user" items="${model.users}">
		<div class="row">

			<div class="col-md-6">

				<h3>
					<c:out value="${user.firstName} ${user.lastName}" />
				</h3>
				<img src="<c:url value="/assets/img/Placeholder.png" /> " style="width:50px; height:50px;">
				<br>
				<h5>
					<c:out value="${user.business.gender}, ${user.business.age}" />
				</h5>
				<br>

			</div>
			<div class="col-md-6">
				<br><br>
				<p>
					<strong>Profession: </strong><c:out value="${user.business.profession}" />
					<br>
					<strong>Education level: </strong><c:out value="${user.business.education}" />
				</p>
			</div>
		</div>
		<form method="POST" action="../connections/view">
			<button class="btn" value="${user.id}" name="id" style="background-color: #000000; color: #ffffff;">Profile</button>
		</form>
		<hr>
	</c:forEach>
	<c:forEach var="job" items="${model.jobs}">
		<div class="row">

			<div class="col-md-6">

				<h3>
					<c:out value="${job.company}" />
				</h3>
				<img src="<c:url value="/assets/img/LocPlaceholder.png" /> " style="width:50px; height:50px;">
				<br>
				<h5>
					<c:out value="${job.city}, ${job.state}" />
				</h5>
				<br>

			</div>
			<div class="col-md-6">
				<br><br>
				<p>
					<strong>Job Title: </strong><c:out value="${job.jobTitle}" />
					<br>
					<strong>Salary: </strong><c:out value="${job.salary} yearly" />
					<br>
					<strong>Field: </strong><c:out value="${job.type}" />
				</p>
			</div>
		</div>
		<p style="font-size: xxsmall;">Posted on: <c:out value="${job.date}"/></p>
		<form method="POST" action="../jobs/view">
			<button class="btn" value="${job.id}" name="id" style="background-color: #000000; color: #ffffff;">Details</button>
		</form>
		<hr>
	</c:forEach>
</div>