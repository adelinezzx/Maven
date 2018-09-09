package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
 
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	 @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf-8");
		 resp.setCharacterEncoding("utf-8");
		super.service(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected  void  outToJson(HttpServletResponse response,Object map) throws IOException {
		Gson  gson = new Gson();
		String gsonStr = gson.toJson(map);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter  out = response.getWriter() ;
		out.println(gsonStr);
		out.flush();
		out.close();
	 
	}

}
