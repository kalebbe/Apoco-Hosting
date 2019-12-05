<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" align="center">
	<h1>Message Center</h1>
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
	
	<div align="right">
		<c:choose>
			<c:when test="${sessionScope.msgType.equals('inbox')}">
				<a href="../messages/outbox">View Sent</a>
			</c:when>
			<c:otherwise>
				<a href="../messages/inbox">View Inbox</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	<hr>
	<c:forEach var="message" items="${messages}">
		<a href="../messages/viewMessage?id=${message.id}" style="text-decoration: none; color: #000000;">
		<c:choose>
			<c:when test="${message.type.equals('socUnread') || message.type.equals('busUnread') ||
				message.type.equals('datUnread') || !message.read}">
				<c:choose>
					<c:when test="${sessionScope.theme.equals('social')}">
						<div class="row" style="background-color: #005024">
					</c:when>
					<c:when test="${sessionScope.theme.equals('business')}">
						<div class="row" style="background-color: #09136e">
					</c:when>
					<c:when test="${sessionScope.theme.equals('dating')}">
						<div class="row" style="background-color: #dd00b9">
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise>
				<div class="row">
			</c:otherwise>
		</c:choose>
			<div class="col-md-2">
				<h4>
					<c:choose>
						<c:when test="${sessionScope.theme.equals('dating')}">
							<c:out value="${message.user.dating.nickname}" />
						</c:when>
						<c:otherwise>
							<c:out value="${message.user.firstName} ${message.user.lastName}"/>
						</c:otherwise>
					</c:choose>
					
				</h4>
				<img src="<c:url value="/assets/img/Placeholder.png" /> " height="50" width="50">
			</div>
			<div class="col-md-8" align="left">
				<br>
				<p>
					<c:out value="${message.body}" />
				</p>
			</div>
			<div class="col-md-2" align="right">
				<p style="opacity: 0.5">
					<c:out value="${message.date}"/>
				</p>
			</div>
		</div>
		</a>
		<hr>
	</c:forEach>
	
</div>