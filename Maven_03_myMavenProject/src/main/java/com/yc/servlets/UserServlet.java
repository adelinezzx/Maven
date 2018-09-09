package com.yc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.bean.DataGridModel;
import com.yc.bean.Users;
import com.yc.server.biz.UserBiz;

@WebServlet("/user.action")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserBiz ub = new UserBiz();
	private String op = null;
	protected int page; // easy-ui分页 ：有多少页
	protected int rows;// easy-ui分页：行
	protected String sort;// easy-ui分页 排序的列名
	protected String order;// easy-ui分页 排序的方式

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 
		  if(request.getParameter("op") != null &&  !"".equals(request.getParameter("op"))   ){
			  op=request.getParameter("op");
		  } 
		 
		try {
			if (op.equals("reg")) { // 注册
				regOp(request, response);
			} else if (op.equals("login")) { // 登录
				loginOp(request, response);
			} else if (op.equals("showAllUsers")) { // 用户列表  
				showAllUsers(request, response);
			} else if (op.equals("UpdateUserInfo")) { // 用户列表 UpdateUserInfo
				UpdateUserInfo(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void UpdateUserInfo(HttpServletRequest request, HttpServletResponse response) {
		 
		
	}

	private void showAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<Users> users = ub.getAllUSersCount() ;
		int total = users.size();  //得到所有用户的总数
		int start = (page - 1) * rows;
		int pagesize  = 5;
		List<Users>  list = ub.getAllUsers(start,pagesize);
		DataGridModel dgm = new DataGridModel();
		dgm.setRows(list);
		dgm.setTotal(total);

		outJsonString(response, dgm);

	}

	/**
	 * 将对象转化为json字符串格式
	 * 
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	protected void outJsonString(HttpServletResponse response, Object obj) throws IOException {
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonString);
		out.flush();
	}

	/**
	 * 登录
	 */
	private void loginOp(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);

		Users u = ub.testLogin(user);

		if (u != null) {
			request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.println("login failure   ");
			out.flush();
			out.close();
		}

	}

	/**
	 * 注册
	 */
	private void regOp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String tel = request.getParameter("tel");
		String email = request.getParameter("email");

		if ((username != null && !username.equals("")) || (password != null && !password.equals(""))) {

			if ((tel != null && !tel.equals("")) || (email != null && !email.equals(""))) {
				Users user = new Users();
				user.setUsername(username);
				user.setPassword(password);
				user.setTel(tel);
				user.setEmail(email);
				int result = ub.addUser(user);
				if (result > 0) {
					request.getRequestDispatcher("index.html").forward(request, response);
				} else {
					PrintWriter out = response.getWriter();
					out.println("add failure   ");
					out.flush();
					out.close();
				}

			}

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
