<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<script>
	//jQuery for autosizing textareas on page load
	$(function() {
		$("textarea").each(function() {
			this.style.height = (this.scrollHeight + 10) + 'px';
		});
	});
</script>
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
				<c:out value="${user.firstName} ${user.lastName}" />
			</h3>
			<img src="<c:url value="/assets/img/Placeholder.png" /> "> <br>
			<p>
				<c:out
					value="${user.social.gender}, ${user.social.age}, ${user.social.status}" />

				<br> <strong>Education:</strong>&nbsp
				<c:out value="${user.social.education}" />
				<br> <strong>Career:</strong>&nbsp
				<c:out value="${user.social.career}" />
				<br> <strong>Location:</strong>&nbsp
				<c:out value="${user.social.city}, ${user.social.state}" />
			</p>
		</div>
		<div class="col-md-9">

			<p>
				<strong>Biography</strong><br>
				<c:out value="${user.social.bio}" />
				<br> <br> <strong>Current/Most Recent School</strong><br>
				<c:out value="${user.social.school}" />
				<br> <br> <strong>Current/Most Recent Job</strong><br>
				<c:out value="${user.social.job}" />
			</p>
		</div>
	</div>
	<c:choose>
		<c:when test="${!sessionScope.profile.equals('user')}">
			<c:choose>
				<c:when test="${user.friend == true}">
					<div align="center">
						<button class="btn" id="sendReq" onclick="ajaxFeed('friends/sendRequest', 'id', '#sendReq')"
							style="background-color: #000000; color: #ffffff;"
							value="${user.id}">Send Friend Request</button>
					</div>
				</c:when>
				<c:otherwise>
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
						
						<button class="btn" id="remFriend" onclick="ajaxFeed('friends/removeFriend', 'id', '#remFriend')"
							style="background-color: #a70000; color: #ffffff;"
							value="${user.id}">Remove Friend</button>
					</div>
				</c:otherwise>
			</c:choose>
		</c:when>
	</c:choose>
	<div align="center">
		<h2>User Posts</h2>
		<br>
		<table>
			<c:choose>
			<c:when test="${sessionScope.message2 != null}">
				<p class="error">
					<c:out value="${sessionScope.message2}" />
				</p>
				<c:remove var="message2" />
			</c:when>
			<c:when test="${sessionScope.message3 != null}">
				<p>
					<c:out value="${sessionScope.message3}" />
				</p>
				<c:remove var="message3" />
			</c:when>
		</c:choose>
			<c:forEach var="feed" items="${user.feed}">
				<tr>
					<td>
						<p style="opacity: 0.5;">
							<c:choose>
								<c:when test="${feed.userId == sessionScope.id}">
										You said:
									</c:when>

								<c:otherwise>
									<c:out value="${feed.name}" />
									said:
								</c:otherwise>
							</c:choose>
						</p>
					</td>
				</tr>
				<tr>
					<td><c:choose>
							<c:when test="${feed.userId == sessionScope.id}">
								<div
									onclick="$('#feedTarget${count}').attr('disabled', false); $('#target${count}').css('display', 'inline-block');">

									<textarea id="feedTarget${count}" cols="40" disabled="disabled"
										name="feed" minlength="20" maxlength="5000"
										style="white-space: pre-wrap; border: none; outline: none; background-color: #484848; color: #ffffff; overflow: hidden; resize: none"
										oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"'><c:out
											value="${feed.feed}" /></textarea>
								</div>
								<div id="target${count}" style="display: none;">
									<button class="btn" id="idTarget${count}" value="${feed.id}"
										name="id"
										onclick="ajaxFeed('feed/updateFeed', 'id', '#idTarget${count}', 'feed', '#feedTarget${count}')"
										style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue;">
										Update</button>
									<br>
								</div>
								
							</c:when>
							<c:otherwise>
								<p style="white-space: pre-wrap"><c:out value="${feed.feed}" /></p>
							</c:otherwise>
						</c:choose>
						<div class="row">
							<div class="col-md-4">
								<c:choose>
									<c:when test="${feed.vote.equals('Like')}">
										<button class="btn" id="likeTarget${count}" value="${feed.id}"
											onclick="ajaxFeed('feed/likeFeed', 'id', '#likeTarget${count}')"
											style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: #0fb800; font-size: small;">Like</button>
									</c:when>
									<c:when test="${feed.vote.equals('Dislike')}">
										<button class="btn" disabled
											style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue; font-size: small;">Like</button>
									</c:when>
									<c:otherwise>
										<button class="btn" id="likeTarget${count}" value="${feed.id}"
											onclick="ajaxFeed('feed/likeFeed', 'id', '#likeTarget${count}')"
											style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue; font-size: small;">Like</button>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-md-4">
								<p style="font-size: small;">
									<c:out value="${feed.votes}" />
								</p>
							</div>
							<div class="col-md-4">
								<c:choose>
									<c:when test="${feed.vote.equals('Dislike')}">
										<button class="btn" id="dislikeTarget${count}"
											value="${feed.id}"
											onclick="ajaxFeed('feed/dislikeFeed', 'id', '#dislikeTarget${count}')"
											style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: #a70000; font-size: small;">Dislike</button>
									</c:when>
									<c:when test="${feed.vote.equals('Like')}">
										<button class="btn" disabled
											style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue; font-size: small;">Dislike</button>
									</c:when>
									<c:otherwise>
										<button class="btn" id="dislikeTarget${count}"
											value="${feed.id}"
											onclick="ajaxFeed('feed/dislikeFeed', 'id', '#dislikeTarget${count}')"
											style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue; font-size: small;">Dislike</button>
									</c:otherwise>
								</c:choose>
							</div>
						</div></td>
				</tr>
				<div>
					<c:choose>
						<c:when test="${feed.link != null}">
							<tr>
								<td>
									<div class="embed-responsive embed-responsive-16by9">
										<iframe id="video" class="embed-responsive-item"
											src="https://www.youtube.com/embed/${feed.link}"></iframe>
									</div>
								</td>
							</tr>
						</c:when>
					</c:choose>
				</div>
				<c:choose>
					<c:when test="${feed.userId == sessionScope.id}">
						<tr
							style="border-bottom: 1px solid rgba(0, 0, 0, .1); margin-bottom: 50px;">
							<td align="center">
								<p style="font-size: small; font-weight: bold;">
									<c:out
										value="${feed.privacy.equals('public') ? 'Everyone' : 'Friends'}" />
									can see your post
									<button value="${feed.id}" name="id" id="idTarget${count}"
										onclick="ajaxFeed('feed/deleteFeed', 'id', '#idTarget${count}')"
										style="background: none !important; color: inherit; border: none; padding: 0 !important; font: inherit; cursor: pointer; color: blue; font-size: small;">Remove</button>
								</p>
							</td>
						</tr>
					</c:when>
				</c:choose>
				<c:set var="count" value="${count + 1}" scope="page" />
			</c:forEach>
		</table>
	</div>
</div>