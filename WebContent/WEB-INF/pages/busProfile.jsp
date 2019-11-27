<!-- 
   - Authors:		  Kaleb Eberhart, Kaleb Eberhart
   - Since:           11/25/18
   - Version:  		  1.0
   - Summary:         This is the view used for creation of the business profile. 
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<h1 class="text-center">Business Profile</h1>
	<br> <br> <br>
	<form:form method="POST" modelAttribute="business" action="submitBus">
		<div class="row">
			<div class="col-md-6">
				<h5>
					Occupation:
					<form:select path="profession">
						<form:options items="${jobList}" />
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
					Date of Birth:
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
					<br> <br> Gender:
					<form:radiobutton path="gender" value="Male" label="Male" />
					&nbsp
					<form:radiobutton path="gender" value="Female" label="Female" />
				</h5>
			</div>
		</div>
		<br> <br>
		<div align="center">
			<button class="btn" type="submit"
				style="background-color: #000000; color: #ffffff;">Create
				Profile</button>

		</div>
	</form:form>
</div>