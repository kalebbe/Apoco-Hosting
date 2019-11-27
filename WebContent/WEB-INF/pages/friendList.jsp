<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<div class="container" align="center">
	<c:choose>
		<c:when test="${sessionScope.page.equals('search')}">
			<h1>Search Results</h1>
		</c:when>
		<c:when test="${sessionScope.page.equals('requests')}">
			<h1>New Friend Requests</h1>
		</c:when>
		<c:otherwise>
			<h1>Friend List</h1>
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
	<c:forEach var="user" items="${users}">
		<div class="row">

			<div class="col-md-6">

				<h3>
					<c:out value="${user.firstName} ${user.lastName}" />
				</h3>
				<img src="<c:url value="/assets/img/Placeholder.png" /> ">
				<br>
				<h5>
					<c:out value="${user.social.gender}, ${user.social.age}" />
				</h5>
				<br>

			</div>
			<div class="col-md-6">
				<br><br>
				<p>
					<c:out value="${user.social.bio}" />
				</p>
			</div>
		</div>
		<c:choose>
			<c:when test="${sessionScope.page.equals('requests')}">
				<input type="hidden" value="${user.id}" id="userId">
				<button class="btn" style="background-color: #0fb800; color: #ffffff;" value="${user.messageId}"
					id="accept" onclick="ajaxFeed('friends/addFriend', 'id', '#accept', 'friendId', '#userId')">Accept Request</button>
				<button class="btn" style="background-color: #a70000; color: #ffffff;" value="${user.messageId}"
					id="deny" onclick="ajaxFeed('friends/denyRequest', 'id', '#deny')">Deny Request</button>
			</c:when>
			<c:otherwise>
				<form method="POST" action="../friends/view">
					<button class="btn" value="${user.id}" name="id" style="background-color: #000000; color: #ffffff;">View Profile</button>
				</form>
			</c:otherwise>
		</c:choose>
		<hr>
	</c:forEach>
</div>