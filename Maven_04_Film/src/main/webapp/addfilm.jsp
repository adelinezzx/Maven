<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script  src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
   $(function(){
	   $("#msg").html('  ');
	    $.ajax({
	    	 url:'film.action?op=allType',
	    	 type:'POST',
	    	 dateType:'JSON',
	    	 success:function(data){
	    		 if(data.code==1){
	    			 $.each(data.list,function(index,item){
	    				 /*  循环得出 电影类型  再加入 选择框中 */
	    				 $("<option value="+item.typename+"> "+item.typename+"</option>").appendTo($("#typename"))
	    			 });
	    		 }
	    	 }
	    	
	    });
	    $("#button").click(function(){
	    	$.ajax({
	    		 url:'film.action?op=addFilm',
			     type:'POST', 
			     dataType:'JSON',
			     data:$("#myform").serialize(),
			     success:function(data){
			    	 $("#result").html('  ');
			    	 if(data.code ==1){
			    		 $("#msg").html("添加成功！");
					}else{
						$("#msg").html("添加电影失败，请重新添加");
					}
				}
	    	});
	    });
   });
   </script>
</head>
<body>
<form   method="post" id ="myform">
    电影名称：<input type="text" id ="filmname" name="filmname"/>  
    电影类型：<select id="typename" name="typename">  
                      </select>  
    主演：<input type="text" id ="actor" name="actor"/>  
    导演：<input type="text" id ="director" name="director"/>  
    价格范围：<input type="text" id ="ticketprice" name="ticketprice" /><br/>
                 <input type="button"  id="button" name="button"  value="确认添加">
                  
</form>

<div id="msg"></div>
</body>
</html>