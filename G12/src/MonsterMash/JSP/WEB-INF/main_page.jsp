<%-- 
    Document   : main_page
    Created on : 2012-12-10, 12:47:05
    Author     : Toshiba
--%>

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
</head>
<body>
	<div id="friendslist">
		<ul class="list">
			<li class="friend-request">
				<a href="#"><img src="images/avatar.jpg" alt="" /> <span style="font-weight:normal;">Friend request:</span> Friend 0</a>
				<ul class="subrequest">
					<li><a href="#">Accept Request</a></li>
					<li><a href="#">Cancel Request</a></li>
				</ul>
			</li>
			<li class="friend-request">
				<a href="#"><img src="images/avatar.jpg" alt="" /> <span style="font-weight:normal;">Friend request:</span> Friend</a>
				<ul class="subrequest">
					<li><a href="#">Accept Request</a></li>
					<li><a href="#">Cancel Request</a></li>
				</ul>
			</li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Friend 1</a></li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Friend 2</a></li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Friend 3</a></li>
		</ul>
	</div>
	<div id="friend-request">
		<form action="" method="post">
			<p>Send friend request:</p>
			<input type="text" maxlength="255" />
		</form>
	</div>
	<div id="monsterlist">
		<ul class="list">
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Monster 1</a></li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Monster 2</a></li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Monster 3</a></li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Monster 4</a></li>
			<li><a href="#"><img src="images/avatar.jpg" alt="" /> Monster 5</a></li>
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
			<h3>
				<div style="float:left;"><b>Player Xxx</b> attacked your monster <b>Yyyy</b>.</div>
				<div class="date">3 minutes ago</div>
				<div style="clear:both;"></div>
			</h3>
			<div>
				<p>
				Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer
				ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit
				amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut
				odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.
				</p>
			</div>
			<h3>
				<div style="float:left;"><b>Player Zzz</b> offered you <b>Xyz</b> for <b>Zyxzc</b>.</div>
				<div class="date">30 minutes ago</div>
				<div style="clear:both;"></div>
			</h3>
			<div>
				<p>
					Offer details.
				</p>
			</div>
			<h3>
				<div style="float:left;">You have sold <b>Yzxc</b> for <b>Zx</b>.</div>
				<div class="date">2 hours ago</div>
				<div style="clear:both;"></div>
			</h3>
			<div>
				<p>
				Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
				Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero
				ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis
				lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.
				</p>
				<ul>
					<li>List item one</li>
					<li>List item two</li>
					<li>List item three</li>
				</ul>
			</div>
			<h3>
				<div style="float:left;">You won Monster Mash with <b>Player Xyz</b>.</div>
				<div class="date">5 hours ago</div>
				<div style="clear:both;"></div>
			</h3>
			<div>
				<p>
				Cras dictum. Pellentesque habitant morbi tristique senectus et netus
				et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in
				faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia
				mauris vel est.
				</p>
				<p>
				Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
				Class aptent taciti sociosqu ad litora torquent per conubia nostra, per
				inceptos himenaeos.
				</p>
			</div>
		</div>
	</div>
</body>
</html>
