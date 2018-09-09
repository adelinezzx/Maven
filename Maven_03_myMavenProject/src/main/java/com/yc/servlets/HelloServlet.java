package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import com.yc.bean.Users;
import com.yc.server.biz.UserBiz;

@WebServlet("/hello.action")
public class HelloServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		 
		try {
		 
				doLogin(request, response);
		 
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user =  new Users();
		
		user.setPassword(password);
		user.setUsername(username);
		 
		Users u  = ub.testLogin(user);
		if (u != null) {
			// 查询数据库是否有该条数据
			request.getRequestDispatcher("success.html").forward(request, response);

		} else {
			PrintWriter out = response.getWriter();
			out.println("hello word ");
			out.flush();
			out.close();
		}*/

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
