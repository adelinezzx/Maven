<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>
<script type="text/javascript" src="js/home.js"></script>

<div id="postform">
	<form method="POST" action="post.html" id="postTopicForm">
		${loginuser.username } 有啥感想? <br> <input type="hidden"
			name="userid" value="${loginuser.userid }" /> <input type="hidden"
			name="op" value="postTopic" />


		<table>
			<tr>
				<td><textarea cols="70" rows="3" name="content"></textarea></td>
			</tr>
			<tr>
				<td align="right"><input type="button" id="postBtn" name="doit"
					value="Update"></td>
			</tr>
		</table>
	</form>
	<div id="homeinfobox"></div>
</div>
<div class ="post"  id="newTopic">
	<!-- 	<a class="username" href="profile.html?u=test">test</a> hello<br>
			<i>11 分钟前 通过 web发布</i> -->
</div>

<%@ include file="footer.jsp"%>