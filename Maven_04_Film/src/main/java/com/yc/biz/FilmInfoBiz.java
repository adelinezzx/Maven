package com.yc.biz;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.model.FilmInfo;
import com.yc.model.FilmType;

public class FilmInfoBiz {
	
	static SqlSessionFactory factory = null;

	static {
		String resource = "mybatis-config.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public int insertFilmInfo( FilmInfo  filminfo,FilmType filmtype) {
		SqlSession session = null;
	  /*  filmtype = new FilmType( 1,"偶像爱情片");      
	    filminfo  = new FilmInfo( "周星驰", "周星驰", 66, filmtype);*/
		try {
			session = factory.openSession();
			int u = session.insert("com.yc.model.FilmInfoMapper.insertFilmInfo", filminfo);
			if (u > 0) {
				session.commit();
				return u ;
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}
	
	
	public List<FilmInfo> selectComplex( Map<String,Object>   map) {
		SqlSession session = null;
		try {
			session = factory.openSession();
		    List<FilmInfo> u = session.selectList("com.yc.model.FilmInfoMapper.selectComplex", map);
			if (u != null) {
				session.commit();
				return u ;
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
