package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yc.bean.Sysuser;
import com.yc.biz.impl.SysuserImpl;

public class SysuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SysuserImpl si = new SysuserImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op.equals("getAll")) {
			getAllOp(request, response);
		} else if (op.equals("opretor")) {
			opretorOp(request, response);
		} else if (op.equals("update")) {
			oupdateOp(request, response);  
		}else if (op.equals("delete")) {
			deleteOp(request, response);  
		}
	}
	
	/**删除用户信息
	 * @throws IOException */
	private void deleteOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 int id = Integer.parseInt(request.getParameter("id")  );
		 int n = si.delete(id);
		if (n > 0) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('delete success ');  location.href='index.jsp'</script>");
			out.flush();
			out.close();
		}
	}
	
	/**修改用户信息*/
	private void oupdateOp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = Integer.parseInt(request.getParameter("id")  );
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
	 
		if (password.equals(repassword)) {
			Sysuser user = new Sysuser();
			user.setId(id);
			user.setUsername(username);
			user.setPassword(repassword);
			int n = si.update(user);
			if (n > 0) {
				request.getRequestDispatcher("opretorSuccess.jsp").forward(request, response);
			}

		}else{
			System.out.println("密码"+password+"和确认密码"+repassword+"不相同");
		}
		
	}
	/** 对用户信息进行操作
	 * @throws IOException 
	 * @throws ServletException */
	private void opretorOp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id")  );
        Sysuser user = si.getAUser(id);
        if(  user != null ){
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("opretor.jsp").forward(request, response);
        }
	}

	/**显示所有用户*/
	private void getAllOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Sysuser> user = si.getAll();
		Map<String, Object> map = null;
		if (user != null) {
			map = new HashMap<String, Object>();
			map.put("code", 1);
			map.put("list", user);
		}
		outToJson(response, map);
	}

	private void outToJson(HttpServletResponse response, Object map) throws IOException {
		Gson gson = new Gson();
		String gsonStr = gson.toJson(map);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(gsonStr);
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
