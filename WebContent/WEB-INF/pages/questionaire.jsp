<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" align="center">
	<h1>Matchmaking Questionnaire</h1>
	<br><br>
	
	<form:form method="POST" modelAttribute="question" action="submitQue">
	<h5>
	What are you looking to gain from Apoco Dating?
	<br>
	</h5>
	<div align="left" style="width: 35%">
		<p>
		<form:radiobutton path="que1" value="a" label="Friends" />
		<br>
		<form:radiobutton path="que1" value="b" label="Dating" />
		<br>
		<form:radiobutton path="que1" value="c" label="Short-term relationship" />
		<br>
		<form:radiobutton path="que1" value="d" label="Long-term relationship" />
		</p>
	</div>
	<hr>
	<h5>
	How do you feel about religion?
	</h5>
	<div align="left" style="width: 35%;">
		<p>
		<form:radiobutton path="que2" value="a" 
			label="I'm not religious and I don't want my partner to be" />
		<br>
		<form:radiobutton path="que2" value="b"
			label="I'm not religious, but it doesn't matter if they are" />
		<br>
		<form:radiobutton path="que2" value="c"
			label="I am religious, but they don't have to be" />
		<br>
		<form:radiobutton path="que2" value="d"
			label="Fire and brimstone for the pagans!" />
		</p>
	</div>
	<hr>
	<h5>
	How do you feel about politics?
	</h5>
	<div align="left" style="width: 35%">
		<p>
		<form:radiobutton path="que3" value="a"
			label="I'm not political and they shouldn't be either" />
		<br>
		<form:radiobutton path="que3" value="b"
			label="I'm not political, but I don't care if they are" />
		<br>
		<form:radiobutton path="que3" value="c"
			label="I am into politics, but it isn't a driving factor" />
		<br>
		<form:radiobutton path="que3" value="d"
			label="I am into politics and you should be too!" />
		</p>
	</div>
	<hr>
	<h5>
	How do you feel about children?
	</h5>
	<div align="left" style="width: 35%">
		<p>
		<form:radiobutton path="que4" value="a"
			label="Keep the demons away from me!" />
		<br>
		<form:radiobutton path="que4" value="b" label="Maybe some day" />
		<br>
		<form:radiobutton path="que4" value="c"
			label="Definitely looking to have children" />
		<br>
		<form:radiobutton path="que4" value="d"
			label="I already have children, sooooooo" />
		</p>
	</div>
	<hr>
	<h5>
	How would you describe your personality?
	</h5>
	<div align="left" style="width: 35%">
		<p>
		<form:radiobutton path="que5" value="a" label="Leave me alone" />
		<br>
		<form:radiobutton path="que5" value="b"
			label="I keep a tight inner circle" />
		<br>
		<form:radiobutton path="que5" value="c"	
			label="New friends are always welcome" />
		<br>
		<form:radiobutton path="que5" value="d"
			label="You have a pulse, be my friend now!" />
		</p>
	</div>
	
	<button class="btn" type="submit"
		style="background-color: #000000; color:#ffffff;">Submit Answers</button>
	</form:form>
</div>