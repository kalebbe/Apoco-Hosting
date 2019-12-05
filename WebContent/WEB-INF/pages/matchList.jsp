<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="count" value="0" scope="page" />
<div class="container" align="center">
	<h1>Matches</h1>
	<hr>
	<c:forEach var="user" items="${users}">
		<c:if test="${count == 0}">
			<div class="row">
		</c:if>
			<div class="col-md-4">
				<a href="../dating/view?id=${user.id}" style="text-decoration: none; color:#000000;">
					<p>
						<strong><c:out value="${user.dating.nickname}" /></strong><br>
						<c:out value="${user.dating.percentage}" />% Match
						<br>
						<img src="<c:url value="/assets/img/Placeholder.png" />">
					</p>
					
				</a>
				<p>
					<c:out value="${user.dating.gender}, ${user.dating.age}, ${user.dating.status}" />
					<br> <strong>Location:</strong>&nbsp
					<c:out value="${user.dating.city}, ${user.dating.state}" />
				</p>
			</div>
		<c:choose>
			<c:when test="${count == 2}">
				</div>
				<c:set var="count" value="0" scope="page" />
				<hr>
			</c:when>
			<c:otherwise>
				<c:set var="count" value="${count + 1}" scope="page" />
			</c:otherwise>
		</c:choose>
	</c:forEach>
	</div>
</div>