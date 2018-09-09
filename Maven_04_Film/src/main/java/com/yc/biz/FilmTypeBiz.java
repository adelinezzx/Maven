package com.yc.biz;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.model.FilmType;

public class FilmTypeBiz {

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

	// 插入电影类型
	public int insertMany(List<FilmType> filmtype) {
		SqlSession session = null;
		try {
			session = factory.openSession();
			int u = session.insert("com.yc.model.FilmTypeMapper.insertMany", filmtype);
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

	// 通过类型名来查找 类型id
	public FilmType selectTypeid(String filmtype) {
		SqlSession session = null;
		try {
			session = factory.openSession();
			FilmType u = session.selectOne("com.yc.model.FilmTypeMapper.selectTypeid", filmtype);
			if(u!=null){
				session.commit();
				return u;
			}else{
				session.rollback();
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// 查找所有的电影类型
	public List<FilmType> selectAllType() {
		SqlSession session = null;
		try {
			session = factory.openSession();
			List<FilmType> u = session.selectList("com.yc.model.FilmTypeMapper.selectAllType");
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// 通用查找  电影类型
	public List<FilmType> select() {
		SqlSession session = null;
		try {
			session = factory.openSession();
			FilmType  filmtype = new FilmType("历险科幻片");
			List<FilmType> u = session.selectList("com.yc.model.FilmTypeMapper.select",filmtype);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
