<%-- 
    Document   : mating_page
    Created on : 2013-01-29, 14:08:56
    Author     : 
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Monster Mash</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="css/jquery-ui.css" />
	<link rel="stylesheet" href="style.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript">
		$(function() {
			$( "#buylist" ).menu();
		});
		$(function() {
			$( "#selllist" ).menu();
		});
	</script>
</head>
<body>
	<div id="friendslist">
		<ul class="list">
                    <c:forEach items="${friendshipList}" var="friend">
                        <c:if test="${! friend.friendshipConfirmed}">
                            <li class="friend-request">
				<a><img src="images/avatar.jpg" alt="" /> ${friend.email}</a>
				<ul class="subrequest">
					<li><a href="main?acceptFriendRequest=${friend.friendshipID}">Accept Request</a></li>
					<li><a href="main?cancelFriendRequest=${friend.friendshipID}">Cancel Request</a></li>
				</ul>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${friendshipList}" var="friend">
                        <c:if test="${friend.friendshipConfirmed}">
                            <li><a title="Send Fight Request" href="fight?request=${friend.friendID}"><img src="images/avatar.jpg" alt="" /> ${friend.email}</a></li>
                        </c:if>
                    </c:forEach>
		</ul>
	</div>
	<div id="friend-request">
		<form action="main" method="post">
			<p><input id="friendRequestName" type="text" value="Enter email ..." name="email" maxlength="255" /></p>
                        <p><input type="submit" value="Send request" /></p>
		</form>
	</div>
	<div id="monsterlist">
            <ul class="list">
                <c:forEach items="${monsterList}" var="monster">
                    <li><a><img src="images/avatar.jpg" alt="" />${monster.name}</a></li>
                </c:forEach>
            </ul>
	</div>
	<ul class="menu">
		<li id="home"><a href="main-panel.html">Home</a></li>
		<li id="market"><a href="market.html">Market</a></li>
		<li id="mating"><a href="mating.html">Mating</a></li>
		<li id="highscores"><a href="highscores.html">Highscores</a></li>
		<li id="logout"><a href="index.html">Logout</a></li>
	</ul>
	<div class="content">
		<form action="" method="post">
			<h1><span style="font-size: 25px;">F</span>RIENDS MONSTERS:</h1>
			<ul id="buylist">
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
			</ul>
			<hr />
			<h1><span style="font-size: 25px;">M</span>Y MONSTERS:</h1>
			<ul id="selllist">
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
				<li><a href="#"><b>Monster 1</b>: 12 / 63 / 62 | <b>25$</b></a></li>
			</ul>
		</form>
	</div>
</body>
</html>
