<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> </title>
</head>
<body>
  <center> 
   <h2>编辑页面</h2>
   		<form action="sysuser.action?op=update" method="post"> 
   		            <input type="hidden" id="id" name="id" value="${user.id }">
               <p>  用户名： <input  type="text" name="username" id="username"   value= "${user.username}"    />   </p>
              <p>密码：<input  type="password" name="password" id="password"  value= "${user.password}"/> </p>
    		  <p>确认密码：<input  type="password" name="repassword" id="repassword" value= "${user.password}"/> </p>
    		  
    		  
    		  <p><input type="submit" value="确认修改"><a href="index.jsp">返回</a></p>
    		  
    	 </form>
     </center>
     
</body>
</html>