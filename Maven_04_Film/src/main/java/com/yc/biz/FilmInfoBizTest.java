package com.yc.biz;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yc.model.FilmInfo;
import com.yc.model.FilmType;

public class FilmInfoBizTest {

	@Test
	public void testInsertFilmInfo() {
		FilmType   filmtype = new FilmType( 1,"偶像爱情片");      
		FilmInfo    filminfo  = new FilmInfo( "喜剧之王","周星驰", "周星驰", 66, filmtype);
		FilmInfoBiz  fb = new FilmInfoBiz();
		int n = fb.insertFilmInfo(filminfo, filmtype);
		if(n >0 ){
			System.out.println(n);
		}
	}

	@Test
	public void testSelectComplex() {
		 Map<String,Object>  map  = new  HashMap<String,Object>();
		 map.put("filmname", "唐");
		 map.put("typename", "偶像爱情片");
		 map.put("actor", "周星驰");
		 map.put("director", "周星驰");
		 map.put("minprice", 50);
		 map.put("maxprice", 70);
		 FilmInfoBiz  fb = new FilmInfoBiz();
		 List<FilmInfo> n = fb.selectComplex(map);
		if(n != null ){
			System.out.println(n);
		}
	}
}
