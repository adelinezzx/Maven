<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	    			 $("#typename").html("<option>全部</option>")
	    			 $.each(data.list,function(index,item){
	    				 /*  循环得出 电影类型  再加入 选择框中 */
	    				 $("<option value="+item.typename+"> "+item.typename+"</option>").appendTo($("#typename"));
	    			 });
	    		 }
	    	 }
	    	
	    });
	    $("#button").click(function(){
	    	$.ajax({
			     url:'film.action?op=findFilm',
			     type:'POST',
			     dataType:'JSON',
			     data:$("#myform").serialize(),
			     success:function(data){
			    	 $("#result").html('  ');
			    	 if(data.code ==  1){
			    		 $.each(data.list,   function(index,item){
			    		      var str = "<tr><td> "+item.filmid+"</td>";		    		     
			                    str += "<td>" + item.filmname + "</td>";
								str += "<td>" + item.filmtype.typename + "</td>";
								str += "<td>" + item.actor + "</td>";
								str += "<td>" + item.director + "</td>";
								str += "<td>" + item.ticketprice + "</td></tr>";
								$(str).appendTo(  $("#result")   );
						});
					}else{
						 $("#result").html("没有相关电影，请重新搜索");
					}
				}
			});
	    });
	     

	});
</script>
<body>
<h2>电影搜索</h2>
<form   method="post" id ="myform">
    电影名称：<input type="text" id ="filmname" name="filmname"/>  
    电影类型：<select id="typename" name="typename">  
                      </select>  
    主演：<input type="text" id ="actor" name="actor"/>  
    导演：<input type="text" id ="director" name="director"/>  
    价格范围：<input type="text" id ="minprice" name="minprice" />-<input type="text" id ="maxprice" name="maxprice" /> <br/>
                 <input type="button"  id="button" name="button"  value="搜索">
                  <a href="addfilm.jsp">添加电影</a>
</form>
<div id="msg"></div>
<table  border="1" width="600px">
       <thead>
             <tr><td>编号</td><td>电影名</td><td>类型</td><td>主演</td><td>导演</td><td>价格</td></tr>
       </thead>
      <tbody id="result" name="result">
      
      </tbody>
</table>
</body>
</html>
