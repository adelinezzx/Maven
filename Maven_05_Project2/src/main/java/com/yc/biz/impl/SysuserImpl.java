package com.yc.biz.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.bean.Sysuser;

public class SysuserImpl {
	
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
	/*<!--查询所有的用户 -->*/
	public List<Sysuser> getAll(){
		SqlSession session = null;
		try {
			session = factory.openSession();
			List<Sysuser> u = session.selectList("com.yc.model.Sysuser.getAll");
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

	/**查找指定用户的信息*/
	public  Sysuser  getAUser(int id ){
		SqlSession session = null;
		try {
			session = factory.openSession();
			 Sysuser  u = session.selectOne("com.yc.model.Sysuser.getAUser",id);
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
	
	/**更新用户信息*/
	public  int    update(Sysuser  user ){
		SqlSession session = null;
		try {
			session = factory.openSession();
			 int   u = session.update("com.yc.model.Sysuser.update",user);
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
	 return 0 ;
	}
	
	/**删除用户信息*/
	public  int    delete( int id  ){
		SqlSession session = null;
		try {
			session = factory.openSession();
			 int   u = session.delete("com.yc.model.Sysuser.delete",id);
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
	 return 0 ;
	}
}
