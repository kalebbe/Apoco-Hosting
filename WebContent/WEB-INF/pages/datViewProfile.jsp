<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div align="center">
		<c:choose>
			<c:when test="${sessionScope.profile.equals('user')}">
				<h1>Your Profile</h1>
				<br>
			</c:when>
			<c:otherwise>
				<br>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="row">
		<div class="col-md-3">
			<h3>
				<c:out value="${user.dating.nickname}" />
			</h3>
			<img src="<c:url value="/assets/img/Placeholder.png" />"> <br>
			<p>
				<c:out value="${user.dating.gender}, ${user.dating.age}, ${user.dating.status}" />
				
				<br><strong>Education:</strong>&nbsp
				<c:out value="${user.dating.education}" />
				<br><strong>Profession:</strong>&nbsp
				<c:out value="${user.dating.profession}" />
				<br><strong>Ethnicity:</strong>&nbsp
				<c:out value="${user.dating.ethnicity}" />
				<br> <strong>Location:</strong>&nbsp
				<c:out value="${user.dating.city}, ${user.dating.state}" />
				<br><strong>Salary:</strong>&nbsp
				<c:out value="${user.dating.salary}" />
			</p>
		</div>
		<div class="col-md-9">
			<p>
				<br> <br> <strong>Interests:</strong><br>
				<c:out value="${user.dating.interests}" />
				<br><br><strong>About Me:</strong><br>
				<c:out value="${user.dating.about}" />
				<br><br><br><strong>Question 1:</strong>
				What are you looking to gain from Apoco Dating?
				<br><strong>Answer 1:</strong>
				<c:choose>
					<c:when test="${user.dating.question.que1 eq 'a'.charAt(0)}">
						Friends
					</c:when>
					<c:when test="${user.dating.question.que1 eq 'b'.charAt(0)}">
						Dating
					</c:when>
					<c:when test="${user.dating.question.que1 eq 'c'.charAt(0)}">
						Short-term relationship
					</c:when>
					<c:otherwise>
						Long-term relationship
					</c:otherwise>
				</c:choose>
				<br><br><strong>Question 2:</strong>
				How do you feel about religion?
				<br><strong>Answer 2:</strong>
				<c:choose>
					<c:when test="${user.dating.question.que2 eq 'a'.charAt(0)}">
						I'm not religious and I don't want my partner to be
					</c:when>
					<c:when test="${user.dating.question.que2 eq 'b'.charAt(0)}">
						I'm not religious, but it doesn't matter if they are
					</c:when>
					<c:when test="${user.dating.question.que2 eq 'c'.charAt(0)}">
						I am religious, but they don't have to be
					</c:when>
					<c:otherwise>
						Fire and brimstone for the pagans!
					</c:otherwise>
				</c:choose>
				<br><br><strong>Question 3:</strong>
				How do you feel about politics?
				<br><strong>Answer 3:</strong>
				<c:choose>
					<c:when test="${user.dating.question.que3 eq 'a'.charAt(0)}">
						I'm not political and they shouldn't be either
					</c:when>
					<c:when test="${user.dating.question.que3 eq 'b'.charAt(0)}">
						I'm not political, but I don't care if they are
					</c:when>
					<c:when test="${user.dating.question.que3 eq 'c'.charAt(0)}">
						I am into politics, but it isn't a driving factor
					</c:when>
					<c:otherwise>
						I am into politics and you should be too!
					</c:otherwise>
				</c:choose>
				<br><br><strong>Question 4:</strong>
				How do you feel about children?
				<br><strong>Answer 4:</strong>
				<c:choose>
					<c:when test="${user.dating.question.que4 eq 'a'.charAt(0)}">
						Keep the demons away from me!
					</c:when>
					<c:when test="${user.dating.question.que4 eq 'b'.charAt(0)}">
						Maybe some day
					</c:when>
					<c:when test="${user.dating.question.que4 eq 'c'.charAt(0)}">
						Definitely looking to have children
					</c:when>
					<c:otherwise>
						I already have children, sooooooo
					</c:otherwise>
				</c:choose>
				<br><br><strong>Question 5:</strong>
				How would you describe your personality?
				<br><strong>Answer 5:</strong>
				<c:choose>
					<c:when test="${user.dating.question.que5 eq 'a'.charAt(0)}">
						Leave me alone
					</c:when>
					<c:when test="${user.dating.question.que5 eq 'b'.charAt(0)}">
						I keep a tight inner circle
					</c:when>
					<c:when test="${user.dating.question.que5 eq 'c'.charAt(0)}">
						New friends are always welcome
					</c:when>
					<c:otherwise>
						You have a pulse, be my friend now!
					</c:otherwise>
				</c:choose>
			</p>
		</div>
	</div>
	<c:if test="${!sessionScope.profile.equals('user')}">
		<div align="center">
			<form method="POST" action="../messages/send">
				<input type="hidden" name="userId" value="${user.id}" />
				<input type="hidden" name="parentId" value="-1" />
				<textarea name="body"
					style="white-space: pre-wrap: overflow:hidden;" cols="50" rows = "5"
					minlength="10" maxlength="50000" 
					oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"'></textarea>
				<br><br>
				<button class="btn" type="submit" style="background-color: #000000; color: #ffffff;">Send Message</button>
			</form>
		</div>
	</c:if>
</div>