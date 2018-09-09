<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>redis微博实现</title>
<script type="text/javascript" src="js/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/header.js"></script>
 
<!--header.js :页面一家在  在判断用户是否已经在登录  -->
</head>
<body>
	<div id="page">
		<div id="header">
			<a href="/">
			   <img style="border: none" src="logo.png" width="192"
				height="85" alt="Retwis"></a>
			<div id="navbar">
				<a href="index.jsp">主页</a> | <a href="timeline.jsp">热点</a> | 
				<a id="logintype"	href="logout.jsp">退出</a>
			</div>
		</div>
	</div>
</body>
</html>