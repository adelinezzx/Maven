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
import com.yc.biz.FilmInfoBiz;
import com.yc.biz.FilmTypeBiz;
import com.yc.model.FilmInfo;
import com.yc.model.FilmType;

public class FilmServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	FilmInfoBiz fifb = new FilmInfoBiz();
	FilmTypeBiz ftb = new FilmTypeBiz();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if (op.equals("allType")) {
			allTypeOp(request, response);
		} else if (op.equals("findFilm")) {
			findFilm(request, response);
		}else if (op.equals("addFilm")) {
			addFilm(request, response);
		}
	}
	
	/**
	 * 添加电影
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
    private void addFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filmname = request.getParameter("filmname");
		String typename = request.getParameter("typename");

		String actor = request.getParameter("actor");
		String director = request.getParameter("director");
		// String minprice = request.getParameter("minprice");
		double  ticketprice = Double.parseDouble(request.getParameter("ticketprice")   );
		FilmType filmtype = ftb.selectTypeid(typename);
		System.out.println(filmtype);
		FilmInfo filminfo = new FilmInfo(filmname, actor, director, ticketprice, filmtype);
		Map<String, Object> map = null;
		int n = fifb.insertFilmInfo(filminfo, filmtype);
		if (n > 0) {
			map = new HashMap<String, Object>();
			map.put("code", 1);
		}
		super.outToJson(response, map);
	}

	/**
     * 查找电影
     * @param request
     * @param response
     * @throws IOException 
     */
	private void findFilm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String  filmname= request.getParameter("filmname");
		 System.out.println(filmname);
		 String typename = request.getParameter("typename");
		 
		 if("全部".equals(typename)){
			 typename = null ;
		 }
		 String actor = request.getParameter("actor");
		 String director = request.getParameter("director");
		 String minprice = request.getParameter("minprice");
		 String maxprice = request.getParameter("maxprice");
		 
		 Map<String ,Object> map = new HashMap<String , Object>();
		 map.put("filmname", filmname);
		 map.put("typename", typename);
		 map.put("actor", actor);
		 map.put("director", director);
		 map.put("minprice", minprice);
		 map.put("maxprice", maxprice);
		 
		List<FilmInfo> filminfo =  fifb.selectComplex(map);
		System.out.println(filminfo);
		if(filminfo != null ){
			map = new HashMap<String, Object>();
			map.put("code", 1);
			map.put("list", filminfo);
		}
		super.outToJson(response, map);
		
	}

	/**
	 * 显示电影类型
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void allTypeOp(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<FilmType> list = ftb.selectAllType();
		Map<String, Object> map = null;
		if (list != null) {
			map = new HashMap<String, Object>();
			map.put("code", 1);
			map.put("list", list);
		}
		super.outToJson(response, map);
	}

}
