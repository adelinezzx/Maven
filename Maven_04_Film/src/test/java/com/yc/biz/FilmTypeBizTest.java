package com.yc.biz;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yc.model.FilmType;

public class FilmTypeBizTest {

	@Test
	public void testTestLogin() {
		List<FilmType>  list = new ArrayList<FilmType>();
		list.add(  new FilmType("偶像爱情片"));
		list.add(  new FilmType("宫斗权谋片"));
		list.add(  new FilmType("玄幻史诗片"));
		list.add(  new FilmType("都市生活片"));
		list.add(  new FilmType("罪案谍战片"));
		list.add(  new FilmType("历险科幻片"));
		
		FilmTypeBiz  fb = new FilmTypeBiz() ;
		int n = fb.insertMany(list);
		if(n>0){
			System.out.println(list);
		}
	}
	
	@Test
	public void testSelectTypeid() {
		FilmTypeBiz  fb = new FilmTypeBiz() ;
		FilmType n = fb.selectTypeid("历险科幻片");
		if(n !=null ){
			System.out.println(n);
		}
	}
	
	@Test
	public void testSelectAllType() {
		FilmTypeBiz  fb = new FilmTypeBiz() ;
		List<FilmType> n = fb.selectAllType();
		if(n !=null ){
			System.out.println(n);
		}
	}
	@Test
	public void testSelect() {
		FilmTypeBiz  fb = new FilmTypeBiz() ;
		//FilmType  name = new FilmType("历险科幻片");
		List<FilmType> n = fb.select();
		if(n !=null ){
			System.out.println(n);
		}
	}
}
