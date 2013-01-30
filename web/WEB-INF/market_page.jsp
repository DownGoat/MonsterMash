<%-- 
    Document   : market_page
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
                    <c:if test="${monster.saleOffer == 0}" >
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
            <form action="" method="post">
                <h1><span style="font-size: 25px;">B</span>UY <span style="font-size: 25px;">F</span>RIEND'S <span style="font-size: 25px;">M</span>ONSTERS:</h1>
                <ul id="buylist">
                    <c:if test="${empty monstersForSale}">
                        <li style="text-align:center;">No offers at the moment.</li>
                    </c:if>
                    <c:forEach items="${monstersForSale}" var="monster">
                        <li><a href="market?monster=${monster.id}&server=${monster.serverID}"><b>Name:</b> ${monster.name} | <b>Price:</b> ${monster.saleOffer}</a></li>
                    </c:forEach>
                </ul>
                <hr />
                <h1><span style="font-size: 25px;">Y</span>OUR <span style="font-size: 25px;">M</span>ONSTERS <span style="font-size: 25px;">F</span>OR <span style="font-size: 25px;">S</span>ALE :</h1>
                <ul id="selllist">
                    <c:set var="anyOffer" value="0" />
                    <c:forEach items="${monsterList}" var="monster">
                        <c:if test="${monster.saleOffer != 0}" >
                            <c:set var="anyOffer" value="1" />
                            <li><a href="#" title="Cancel your offer"><b>Name:</b> ${monster.name} | <b>Price:</b> ${monster.saleOffer}</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${anyOffer == 0}">
                        <li style="text-align:center;">You are not selling any monsters at the moment.</li>
                    </c:if>
                </ul><br />
                <hr />
                <h1><span style="font-size: 25px;">S</span>ELL <span style="font-size: 25px;">Y</span>OUR <span style="font-size: 25px;">M</span>ONSTER:</h1>
                <form action="" method="POST">
                    <p style="text-align:center">Choose monster:</p>
                    <p style="text-align:center">
                        <select name="monster">
                            <c:forEach items="${monsterList}" var="monster">
                                <c:if test="${monster.saleOffer == 0}" >
                                    <option value="${monster.id}">${monster.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </p>
                    <p style="text-align:center">Your offer:</p>
                    <p style="text-align:center"><input type="text" name="amount" /></p>
                    <p style="text-align:center"><input type="submit" value="Sell monster" /></p>
                </form>
            </form>
        </div>
    </body>
</html>
