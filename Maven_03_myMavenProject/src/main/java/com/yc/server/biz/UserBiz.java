package com.yc.server.biz;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yc.bean.Users;

public class UserBiz {

	static SqlSessionFactory factory = null;

	static {
		String resource = "Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 登录
	public Users testLogin(Users user) {
		SqlSession session = null;
		try {
			session = factory.openSession();
			Users u = session.selectOne("com.yc.dao.mapper.userMapper.login", user);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// 注册
	public int addUser(Users user) {
		SqlSession session = null;
		int result = 0;
		try {
			session = factory.openSession();
			result = session.insert("com.yc.dao.mapper.userMapper.addUser", user);
			if (result >= 1) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	// 更新用户信息
	public int updateUser(Users user) {
		SqlSession session = null;
		int result = 0;
		try {
			session = factory.openSession();
			result = session.update("com.yc.dao.mapper.userMapper.updateUser", user);
			if (result >= 1) {
				session.commit();
			} else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}

	// 得到所有用户的个数
	public  List<Users>  getAllUSersCount() {
		SqlSession session = null;
		try {
			 session = factory.openSession();
			 List<Users> result  = session.selectList("com.yc.dao.mapper.userMapper.getAllUserCount");
			 return result ;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}

	//用户列表  
	public List<Users> getAllUsers(int  start, int  pagesize) {
		SqlSession session = null;
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("start", start);
		map.put("pagesize", pagesize);
		try {
			session = factory.openSession();
			List<Users> result  = session.selectList("com.yc.dao.mapper.userMapper.showAllUsers",map);
			return result ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
