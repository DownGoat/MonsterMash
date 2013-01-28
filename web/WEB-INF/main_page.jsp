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
		});
	</script>
        <script type="text/javascript">
        function sendFriendRequest(){
            var xmlhttp;
            if (window.XMLHttpRequest){
                // code for IE7+, Firefox, Chrome, Opera, Safari
                xmlhttp=new XMLHttpRequest();
            }else{
                // code for IE6, IE5
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange = function(){
                    if(xmlhttp.readyState==4 && xmlhttp.status==200){
                        var updates = document.getElementById('updates');
                        var currentTime = new Date();
                        var day = ("0" + currentTime.getDate()).slice(-2);
                        var month = ("0" + (currentTime.getMonth() + 1)).slice(-2);
                        var year = currentTime.getFullYear();
                        var date = year+"-"+month+"-"+day;
                        updates.innerHTML = "<h3><div style=\"float:left;\">"+xmlhttp.responseText+"</div><div class=\"date\">"+date+"</div><div style=\"clear:both;\"></div></h3><div><p>"+xmlhttp.responseText+"</p></div>"+updates.innerHTML;
                        $( "#updates" ).accordion( "destroy" );
                        $( "#updates" ).accordion();
                    }
                }
            var friendEmail = document.getElementById('friendRequestName').value;
            xmlhttp.open("POST","main",true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("sendFriendRequest="+friendEmail);
        }
        </script>
</head>
<body>
	<div id="friendslist">
		<ul class="list">
                    <c:forEach items="${friendshipList}" var="friend">
                        <c:if test="${friend.friendshipConfirmed  != 'Y'}">
                            <li class="friend-request">
				<a href="#"><img src="images/avatar.jpg" alt="" /> ${friend.email}</a>
				<ul class="subrequest">
					<li><a href="main?acceptFriendRequest=${friend.id}">Accept Request</a></li>
					<li><a href="main?cancelFriendRequest=${friend.id}">Cancel Request</a></li>
				</ul>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${friendshipList}" var="friend">
                        <c:if test="${friend.friendshipConfirmed  == 'Y'}">
                            <li><a href="player?show=${friend.id}"><img src="images/avatar.jpg" alt="" /> ${friend.email}</a></li>
                        </c:if>
                    </c:forEach>
		</ul>
	</div>
	<div id="friend-request">
		<form action="main" method="post">
			<p><input id="friendRequestName" type="text" value="Enter email ..." name="email" maxlength="255" /></p>
                        <p><button type="button"onclick="sendFriendRequest()">Send request</button></p>
		</form>
	</div>
	<div id="monsterlist">
		<ul class="list">
			<li><a><img src="images/avatar.jpg" alt="" />Random Monster</a></li>
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
		<div id="updates">
                    <c:forEach items="${notificationList}" var="current">
                        <h3>
				<div style="float:left;">${current.text}</div>
				<div class="date">${current.timeSent}</div>
				<div style="clear:both;"></div>
			</h3>
			<div>
				<p></p>
			</div>
                    </c:forEach>
		</div>
	</div>
</body>
</html>
