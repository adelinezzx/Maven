$(function(){
	$.ajax({
		url:'user.action',
		method:'POST',
		data:'op=getnew50user',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				var str = "";
				for(var i = 0 ; i < data.obj.length;i++){
					str+= "<a class='username' href='profile.jsp?username="+data.obj[i].username+"'>"+data.obj[i].username+" </a>"
				}
				$("#userlist").html(str);
			}
		}
	});
	
	
	$.ajax({
		url:'topic.action',
		method:'POST',
		data:'op=getTopic',
		dataType:'JSON',
		success:function(data){
			if(data.code==1){
				var str = "";
				for(var i = 0 ; i < data.obj.length;i++){
					var topic = obj[i];
					str += '<div  class="post"><a class="username" href="profile.jsp?u='
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
})();