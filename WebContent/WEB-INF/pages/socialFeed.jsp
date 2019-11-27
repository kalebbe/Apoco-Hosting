<!-- 
   - Authors:		  Kaleb Eberhart, Mick Torres
   - Since:           10/14/18
   - Version:         1.0
   - Summary:         This is a view that allows the user to upload posts to their feed and view others
   -				  that they have posted. In the future, they will be able to see their friend's posts
   -				  and upload pictures.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="count" value="0" scope="page" />
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<script>
	//jQuery for autosizing textareas on page load
	$(function() {
		$("textarea").each(function() {
			this.style.height = (this.scrollHeight + 10) + 'px';
		});
	});
</script>
<div align="center">
	<h1>Your feed</h1>
	<p style="font-size: xx-small;">Soon you'll be able to post
		pictures here for your friends to see</p>
	<hr>
	<form:form method="POST" modelAttribute="feed" action="createFeed">
		<h5>Youtube Link</h5>
		<form:input path="link" size="40" minlength="10" maxlength="100" />
		<p style="font-size: xx-small;">*Not required. Only for youtube
			videos</p>

		<!-- 
		   - Removes scroll bar + resize button from textarea. Form will auto resize as the
		   - user updates.
		 -->
		<form:textarea
			style="white-space: pre-wrap; resize:none; overflow:hidden;"
			id="word_count" path="feed" cols="40" minlength="20" maxlength="5000"
			oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"' />
		<p style="font-size: xx-small;">
			Word count: <span id="display_count">0</span> of 200 (Max)
		</p>
		<form:errors class="error" path="feed" />
		<h5>
			<form:radiobutton path="privacy" value="public" label=" Public"
				checked="checked" />
			<form:radiobutton path="privacy" value="friends" label=" Friends" />
		</h5>
		<button class="btn" type="submit"
			style="background-color: #000000; color: #ffffff;">Post to
			feed</button>
	</form:form>
	<c:choose>
		<c:when test="${sessionScope.message != null}">
			<p style="font-size: small;">
				<c:out value="${sessionScope.message}" />
			</p>
			<c:remove var="message" />
		</c:when>
	</c:choose>
	<hr />
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
		<c:forEach var="feed" items="${feedList}">
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
							<div onclick="clickDiv(${count})">
								<textarea id="feedTarget${count}" cols="40" disabled="disabled"
									name="feed" minlength="20" maxlength="5000"
									style="white-space: pre-wrap; border: none; outline: none; background-color: #484848; color: #ffffff; resize: none; overflow: hidden;"
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
							<!-- Commenting out for now because this does not work in Firefox. Working on a fix.
							<p style="font-size: xx-small;" align="center">Double click
								your post to edit it!</p>-->
						</c:when>
						<c:otherwise>
							<p style="white-space: pre-wrap">
								<c:out value="${feed.feed}" />
							</p>
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