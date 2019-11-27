<!-- 
   - Authors:		  Kaleb Eberhart, Mick Torres
   - Since:           10/14/18
   - Version:  		  1.0
   - Summary:         This view is used to load the minesweeper game with the desired difficulty
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div align="center">
	<h1>Minesweeper</h1>
	<hr />
	<form method="POST" action="../mines/start">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<button class="btn btn-primary" style="float: right;" name="diff"
						value="1">Play an easy game</button>
				</div>
				<div class="col-md-4">
					<button class="btn btn-warning" name="diff" value="2">Play
						a medium game</button>
				</div>
				<div class="col-md-4">
					<button class="btn btn-danger" style="float: left;" name="diff"
						value="3">Play a hard game</button>
				</div>
			</div>
		</div>
	</form>
	<hr />
	<h1>More coming soon!</h1>
</div>