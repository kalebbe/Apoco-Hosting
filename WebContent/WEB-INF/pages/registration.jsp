<!-- 
   - Authors:		  Kaleb Eberhart, Mick Torres
   - Since:           09/23/18
   - Version:  		  1.0
   - Summary:         This is the registration page for Apoco. Data validation for this is done through both
   -				  JavaScript and Java in the model.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="register-photo">
	<div class="form-container" style="max-width: 411px; color: #000000;">
		<form:form method="POST" modelAttribute="user" action="register" style="background-color: #660000;">
			<h2 class="text-center" style="color: #000000;">
				<strong>Registration</strong>
			</h2>
			<div class="form-group">
				<form:input class="form-control" type="email" path="email" placeholder="Email"/>
				<form:errors style="color: #ffffff; font-size: small;" path="email"/>
				<!-- Responds to the user if their email is taken -->
				<c:choose>
					<c:when test = "${sessionScope.message != null}">
						<p style="color: #ffffff; font-size: small;"><c:out value="${sessionScope.message}"/></p>
						<c:remove var="message"/>
					</c:when>
				</c:choose>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="username" placeholder="Username" minlength="4" maxlength="30" />
				<form:errors style="color: #ffffff; font-size:small;" path="username"/>
				<!-- Responds to the user if their username is taken -->
				<c:choose>
					<c:when test = "${sessionScope.message2 != null}">
						<p style="color: #ffffff; font-size: small;"><c:out value="${sessionScope.message2}"/></p>
						<c:remove var="message2"/>
					</c:when>
				</c:choose>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="firstName" placeholder="First Name" minlength="2" maxlength="30" />
				<form:errors style="color: #ffffff; font-size:small;" path="firstName"/>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="lastName" placeholder="Last Name" minlength="2" maxlength="30" />
				<form:errors style="color: #ffffff; font-size:small;" path="lastName"/>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="password" type="password" placeholder="Password" minlength="8" maxlength="100" />
				<form:errors style="color: #ffffff; font-size:small;" path="password"/>
			</div>
			<div class="form-group">
				<form:input class="form-control" path="passRe" type="password" placeholder="Password (repeat)" minlength="8" maxlength="100" />
				<form:errors style="color: #ffffff; font-size:small;" path="passRe"/>
				<!-- Tells the user if passwords do not match -->
				<c:choose>
					<c:when test = "${sessionScope.message3 != null}">
						<p style="color: #ffffff; font-size: small;"><c:out value="${sessionScope.message3}"/></p>
						<c:remove var="message3"/>
					</c:when>
				</c:choose>
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block" type="submit"
					style="background-color: #000000;">Sign Up</button>
			</div>
			<a href="../login/log" class="already" style="color: #ffffff;">You already have an account? Login here.</a>
		</form:form>
	</div>
</div>