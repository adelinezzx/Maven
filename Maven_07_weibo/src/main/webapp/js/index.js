$(function(){
	$("#doit").click(function(){
		  $.ajax({
			  url:'user.action',
			  method:'POST',
			  data:$("#regForm").serialize(),  //表单中的数据序列化
			  dataType:'JSON',
			  success:function(data){
				  if(data.code == 1){
					  alert("注册成功");
				  }else{
					  alert(data.msg);
				  }
			  }
		  });
	});
	
	
	$("#loginBtn").click(function(){
		$.ajax({
			url:'user.action',
			method:'POST',
			data:$("#loginForm").serialize(),//表单中的数据序列化
			dataType:'JSON',
			success:function(data){
				if(data.code == 1){
					alert("登录成功！");
					location.href='home.jsp';
				}
			}
		});
	});
});