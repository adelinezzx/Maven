package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Users;
import com.yc.server.biz.UserBiz;

 
 @WebServlet("/user.action")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UserBiz ub = new UserBiz() ;
 
	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String  op =  request.getParameter("op");
		/*try {
			if(op.equals("reg")){
				regOp(request,response);
			}else if(op.equals("login")){
				loginOp(request,response);
			}else{
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		PrintWriter out = response.getWriter();
		out.println("hello word ");
		out.flush();
		out.close();
		 
	}

	private void loginOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
	       String password = request.getParameter("password");
	       Users  user = new Users();
	       user.setUsername(username);
	       user.setPassword(password);
	      
	       Users u = ub.testLogin(user);
	      
	       if(u != null){
	    		PrintWriter out = response.getWriter();
				out.println("hello word ");
				out.flush();
				out.close();
	       }
	       
		
	}

	private void regOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 
		 String tel = request.getParameter("tel");
		 String email = request.getParameter("email"); 
		 
		 if( ( username != null && !username.equals("")  ) || (password !=null && !password.equals("") ) ){
			 
			 
			 if(    ( tel != null && !tel.equals("")  ) || (email !=null && !email.equals("") )   ){
				 Users user =new Users();
				 user.setUsername(username);
				 user.setPassword(password);
				 user.setTel(tel);
				 user.setEmail(email);
				 int result = ub.addUser(user);
				 if(result >0 ){
					   PrintWriter out = response.getWriter();
						out.println("add success   ");
						out.flush();
						out.close();
				 }else{
					 PrintWriter out = response.getWriter();
						out.println("add failure   ");
						out.flush();
						out.close();
				 }
				 
			 }
			 
		 }
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
