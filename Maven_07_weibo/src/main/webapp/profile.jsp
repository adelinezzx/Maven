<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/profile.js"></script>

<h2 class="username"><%=request.getParameter("username")%></h2>
<span id="followspan">
    <a href="follow.html?uid=1&f=1" class="button">关注ta</a>
</span>
<div class="post">
	<a class="username" href="profile.html?u=test">test</a> world<br>
	<i>11 分钟前 通过 web发布</i>
</div>

 
<%@include file="footer.jsp"%>