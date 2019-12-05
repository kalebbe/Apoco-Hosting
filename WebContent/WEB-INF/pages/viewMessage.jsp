<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container" align="center">
	<h1>View Conversation</h1>
	<br>
	
	<c:forEach var="message" items="${messages}">
		<div class="row">
			<c:choose>
				<c:when test="${message.user.id == sessionScope.id}">
					<div class="col-md-2">
						<c:choose>
							<c:when test="${sessionScope.theme.equals('social')}">
								<a href="../social/profile" style="text-decoration: none; color: #000000;">
							</c:when>
							<c:otherwise>
								<a href="../business/profile" style="text-decoration: none; color: #000000;">
							</c:otherwise>
						</c:choose>
							<h4>
							<	c:out value="${message.user.firstName} ${message.user.lastName}" />
							</h4>
						<img src="<c:url value="/assets/img/Placeholder.png" /> " height="50" width="50">
						</a>
					</div>
					<div class="col-md-8" align="left">
						<br>
						<p style="white-space: pre-wrap;"><c:out value="${message.body}"/></p>
					</div>
					<div class="col-md-2" align="right">
						<p style="opacity: 0.5">
							<c:out value="${message.date}"/>
						</p>
						<c:choose>
							<c:when test="${message.type.equals('busUnread') || message.type.equals('socUnread')}">
								<p>Unread</p>
							</c:when>
							<c:otherwise>
								<p>Read</p>
							</c:otherwise>
						</c:choose>
					</div>
				</c:when>
				<c:otherwise>
					<div clas="col-md-2" align="left">
						<p style="opacity: 0.5">
							<c:out value="${message.date}"/>
						</p>
					</div>
					<div class="col-md-8" align="right">
						<br>
						<p style="white-space: pre;"><c:out value="${message.body}"/></p>
					</div>
					<div class="col-md-2">
						<c:choose>
							<c:when test="${sessionScope.theme.equals('social')}">
								<a href="../friends/view?id=${message.user.id}" style="text-decoration: none; color: #000000;">
							</c:when>
							<c:when test="${sessionScope.theme.equals('business')}">
								<a href="../connections/view?id=${message.user.id}" style="text-decoration: none; color: #000000;">
							</c:when>
						</c:choose>
							<h4>
								<c:out value="${message.user.firstName} ${message.user.lastName}" />
							</h4>
							<img src="<c:url value="/assets/img/Placeholder.png" /> " height="50" width="50">
						</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<hr>
	</c:forEach>
	
	<form method="POST" action="../messages/send">
		<input type="hidden" name="userId" value="${sessionScope.recId}" />
		<input type="hidden" name="parentId" value="${messages[0].id}" />
		<textarea name="body"
			style="white-space: pre-wrap: overflow:hidden;" cols="50" rows = "5"
			minlength="10" maxlength="50000" 
			oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"'></textarea>
		<br><br>
		<button class="btn" type="submit" style="background-color: #000000; color: #ffffff;">Send Message</button>	
	</form>
</div>