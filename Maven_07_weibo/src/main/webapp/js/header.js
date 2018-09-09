$(

		function() {
			// 当页面一加载时，就要访问服务 查看自己是否登录
			$
					.ajax({
						url : 'user.action',
						method : 'POST',
						data : 'op=checklogin',
						dataType : 'JSON',
						success : function(data) {
							if (data.code == 0) {
								$("#logintype").html(
										'<a href="index.jsp">登录</a>');
							} else {
								$("#logintype")
										.html(
												'欢迎您：'
														+ data.obj.username
														+ ' | <a href ="#" onclick="loginout();return false;">退出</a>');
							}
						}
					});

		});

function loginout() {
	$.ajax({
		url : 'user.action',
		method : 'POST',
		data : 'op=loginout',
		dataType : 'JSON',
		success : function(data) {
			if (data.code == 1) {
				location.href = 'index.jsp';
			}
		}
	});
}