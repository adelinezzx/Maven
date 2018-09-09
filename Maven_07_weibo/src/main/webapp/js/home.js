$(function() {
	$
			.ajax({
				url : 'topic.action',
				method : 'POST',
				data : 'op=getTopic',
				dataType : 'JSON',
				success : function(data) {
					if (data.code == 1) {
						var str = "";
						for (var i = 0; i < data.obj.length; i++) {
							var topic = obj[i];
							str += '<div ><a class="username" href="profile.jsp?u='
									+ topic.username
									+ ' ">'
									+ topic.username
									+ ' </a>'
									+ topic.content
									+ '<br/><li>'
									+ topic.elapseTime
									+ '分钟前通过web发布	</li></div>';
						}
						$("#newTopic").html(str);
					}
				}

			});

	// 获取粉丝数量
	$
			.ajax({
				url : 'user.action',
				method : 'POST',
				data : 'op=getNumbersOfFansAndCare',
				dataType : 'JSON',
				success : function(data) {
					if (data.code == 1) {
						$("#homeinfobox").html(
								data.obj.fans + "粉丝<br/>" + data.obj.cares
										+ "关注<br/>");
					}
				}
			});
	// 点击发布帖子
	$("#postBtn").click(function() {
		$.ajax({
			url : 'topic.action',
			method : 'POST',
			data : $("#postTopicForm").serialize(),
			dataType : 'JSON',
			success : function(data) {
				if (data.code == 0) {
					alert(data.mag);
					return;
				} else {
					alert("发布成功");
				}

			}
		});
	});
});