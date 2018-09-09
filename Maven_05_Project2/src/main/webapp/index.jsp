<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> </title>
<script  src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
    $(function(){
    	$.ajax({
    		url:'sysuser.action?op=getAll', 
    		type:'POST',
    		data:'JSON',
    		success:function(data){
    			if(data.code == 1){
    				$.each(data.list,function(index,item){
		    		      var str = "<tr><td> "+item.id+"</td>";		    		     
		                    str += "<td>" + item.username + "</td>";
							str += "<td>" + item.password+ "</td> ";
							str +="<td><a href='sysuser.action?op=opretor&id="+item.id+"'>操作</a>--<a href='sysuser.action?op=delete&id="+item.id+"'>删除</a></td></tr> "
							$(str).appendTo(  $("#result")   );
					});
    			}
    		}
    	});
    });
</script>
</head>
<body>
  <center> 
     <table  border="1px" width="600px">
       <thead>
            <tr>
                <td>编号</td><td>用户名</td><td>密码</td><td> 操作 </td>
            </tr>
       </thead>
       <tbody  id="result">
         
       </tbody>
     
     </table>
</center>
</body>
</html>