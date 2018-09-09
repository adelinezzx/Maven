
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/timeline.js"></script>
<h2>热点</h2>
<i>最新注册用户(redis中的sort用法)</i>
<br>
<div id="userlist">
	<a class="username" href="profile.html?u=test">test</a>
</div>

<br>
<i>最新的50条微博!</i>
<br>

<div id="newTopic">
	<div class="post">
		<a class="username" href="profile.html?u=test">test</a> world<br>
		<i>22 分钟前 通过 web发布</i>
	</div>
</div>



<%@ include file="footer.jsp"%>
</body>
</html>