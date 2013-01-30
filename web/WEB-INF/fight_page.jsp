<%-- 
    Document   : main_page
    Created on : 2012-12-10, 12:47:05
    Author     : sjk4
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<title>Monster Mash</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<link rel="stylesheet" href="css/jquery-ui.css" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript" src="js/custom.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript">
		$(function() {
			$( "#updates" ).accordion({
				event: "click"
			});
                        <c:if test="${not empty alertMessage}">
                        alert("${alertMessage}");
                        </c:if>      
		});
	</script>
    </head>
    <body>
	<div id="friendslist">
		<ul class="list">
                    <c:forEach items="${requestList}" var="request">
                        ${request}
                    </c:forEach>
                    <c:forEach items="${friendList}" var="friend">
                        <li><a title="Send Fight Request" href="fight?user=${friend.userID}&server=${friend.serverID}"><img src="images/avatar.jpg" alt="" /> ${friend.username}</a></li>
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
                    <c:if test="${monster.saleOffer == 0 && monster.breedOffer == 0}" >
                        <li><a><img src="images/avatar.jpg" alt="" />${monster.name}</a></li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
	<ul class="menu">
		<li id="home"><a href="main">Home</a></li>
		<li id="market"><a href="market">Market</a></li>
		<li id="mating"><a href="mating">Mating</a></li>
		<li id="highscores"><a href="highscores">Highscores</a></li>
		<li id="logout"><a href="logout">Logout</a></li>
	</ul>
	<div class="content">
            <c:if test="${friendMonsterList.size() != 0}">
                <form action="fight/request" method="GET">
                    <input type="hidden" name="fightID" value="0" /> 
                    <select name="localMonsterID">
                        <c:forEach items="${friendMonsterList}" var="friendMonster">
                            Select enemy: 
                            <option value="${friendMonster.id}">${friendMonster.name}</option>
                        </c:forEach>
                    </select>
                    <select name="remoteMonsterID">
                        <c:forEach items="${monsterList}" var="monster">
                            <c:if test="${monster.saleOffer == 0 && monster.breedOffer == 0}" >
                                Select your monster: 
                                <option value="${monster.id}">${monster.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="remoteServerNumber" value="${remoteServer}" /> 
                    <input type="submit" value="Request fight" />
                </form>
            </c:if>
            <c:if test="${friendMonsterList.size() == 0}">
                This user has no monsters to fight with. 
            </c:if>
	</div>
    </body>
</html>
