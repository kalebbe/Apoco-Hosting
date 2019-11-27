<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" align="center">
	<h1>Dating Profile</h1>
	<!-- Used when age is less than 18 -->
	<c:choose>
		<c:when test="${sessionScope.message != null}">
			<p class= "error">
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
	<br> <br> <br>
	<form:form method="POST" modelAttribute="dating" action="submitDat">
		<div class="row">
			<div class="col-md-6" align="left">
				<h5>
					Occupation:
					<form:select path="profession">
						<form:options items="${jobList}" />
					</form:select>
					<br> <br> Salary Range:
					<form:select path="salary">
						<form:options items="${salaryList}" />
					</form:select>
					<br> <br> Education:
					<form:select path="education">
						<form:options items="${edList}" />
					</form:select>
					<br> <br> Ethnicity:
					<form:select path="ethnicity">
						<form:options items="${ethList}" />
					</form:select>
				</h5>
			</div>
			<div class="col-md-6" align="right">
				<h5>
					Birthday:
					<form:select path="birthDay">
						<form:options items="${dayList}" />
					</form:select>
					<form:select path="birthMonth">
						<form:options items="${monthList}" />
					</form:select>
					<form:select path="birthYear">
						<form:options items="${yearList}" />
					</form:select>
					<br> <br>
				</h5>
				<form:errors class="error" path="city" />
				<h5>
					City:
					<form:input type="text" path="city" minlength="2" maxlength="40" />
					State:
					<form:select path="state">
						<form:options items="${stateList}" />
					</form:select>
					<br> <br> Relationship Status:
					<form:select path="status">
						<form:options items="${statusList}" />
					</form:select>
					<br> <br> Gender:
					<form:radiobutton path="gender" value="Male" label="Male" />
					&nbsp
					<form:radiobutton path="gender" value="Female" label="Female" />
					&nbsp
					<form:radiobutton path="gender" value="Savage" label="Savage" />
				</h5>
			</div>
		</div>
		<br> <br>
		<form:errors class="error" path="nickname" />
		<h5>
			Nickname:
			<form:input type="text" path="nickname" minlength="2" maxlength="20" />
			<br>
		</h5>
			<p style="font-size: xx-small">
				Your will be represented by this nickname on the dating portion of <br>Apoco to help
				protect your identity on the social and business platforms.
			</p>
		<form:errors class="error" path="interests" />
		<h5>
			Interests:
		</h5>
		<form:textarea style="white-space; pre-wrap;" path="interests" rows="3" cols="40"
			minlength="10" maxlegnth="100" 
			oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"'/>
		<br><br>
		<form:errors class="error" path="about" />
		<h5>
			About me:
		</h5>
		<form:textarea style="white-space: pre-wrap;" id="word_count"
				path="about" rows="10" cols="40" minlength="50" maxlength="5000" 
				oninput='this.style.height = "";this.style.height = this.scrollHeight + "px"' />
		<p style="font-size: xx-small;">
			Word count: <span id="display_count">0</span> of 200 (Max)<br>
		</p>
		<button class="btn" type="submit"
			style="background-color: #000000; color:#ffffff;">Create Profile</button>
	</form:form>
</div>