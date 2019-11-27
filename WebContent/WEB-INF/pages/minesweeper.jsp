<!-- 
   - Authors:		  Kaleb Eberhart, Mick Torres
   - Since:           10/14/18
   - Version:  		  1.0
   - Summary:         This is the view used for all of the minesweeper board.
 -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.gcu.utilities.MinesweeperLogic"%>
<script src="<c:url value="/assets/js/ajaxPost.js" />"></script>
<script>
	document.addEventListener('contextmenu', function(e) {
		e.preventDefault();
	}, false);
</script>
<div align="center">
	<h1>Minesweeper</h1>

	<div id="board" class="table-responsive-sm">
		<table>
			<c:forEach var="i" begin="0" end="${sessionScope.size - 1}">
				<tr>
					<c:forEach var="j" begin="0" end="${sessionScope.size - 1}">
						<td><c:set var="button"
								value="${MinesweeperLogic.getBtnHolder()}" scope="page" />
							<div oncontextmenu="return ajaxPost('mines/right', 'btn', '${i} ${j}');">
								<c:choose>
									<c:when test="${button[i][j].visited || button[i][j].flagged}">
										<button id="btn" name="btn"
											style="border: solid; height: 25px; width: 25px; background:"
											value="${i} ${j}" disabled="disabled">
									</c:when>
									<c:otherwise>
										<button id="btn" name="btn"
											onclick="ajaxPost('mines/left', 'btn', '${i} ${j}')"
											style="border: solid; height: 25px; width: 25px; background:"
											value="${i} ${j}">
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${button[i][j].isFlagged()}">
										<img
											style="height: 23px; width: 23px; margin-left: -6px; margin-top: -1px; padding-right: 1px; padding-bottom: 1px;"
											src="<c:url value="/assets/img/social/games/Flag.png" />" />
									</c:when>
									<c:when
										test="${!button[i][j].visited && !button[i][j].isFlagged()}">
										<img
											style="height: 23px; width: 23px; margin-left: -6px; margin-top: -1px; padding-right: 1px; padding-bottom: 1px;"
											src="<c:url value="/assets/img/social/games/MSButton.png" />" />
									</c:when>
									<c:when test="${button[i][j].isLive()}">
										<img
											style="height: 23px; width: 23px; margin-left: -6px; margin-top: -1px; padding-right: 1px; padding-bottom: 1px;"
											src="<c:url value="/assets/img/social/games/Bomb.png" />" />
									</c:when>
									<c:otherwise>
										<c:out value="${button[i][j].liveNeighbors}" />
									</c:otherwise>
								</c:choose>
								</button>
							</div></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<br>
		<c:choose>
			<c:when test="${MinesweeperLogic.isLose()}">
				<h2 style="color: #a70000;">Loser</h2>
				<a class="btn btn-success btn-large" href="../social/games">Play
					again?</a>
				<br>
				<br>
			</c:when>
			<c:when test="${MinesweeperLogic.isWin()}">
				<h2>Winner</h2>
				<a class="btn btn-success btn-large" href="../social/games">Play
					again?</a>
				<br>
				<br>
			</c:when>
		</c:choose>
	</div>

</div>