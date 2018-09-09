package com.yc.server.biz;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
import com.yc.bean.Users;

public class UserBiz {

	static SqlSessionFactory factory = null;

	static  {
		String resource = "Configuration.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//登录
	public  Users testLogin( Users user) {
		SqlSession session = null;
		try {
			session = factory.openSession();
			Users u  =  session.selectOne("com.yc.dao.mapper.userMapper.login",  user  );
            return u;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	//注册
	public  int  addUser( Users user) {
		SqlSession session = null;
			session = factory.openSession();
			 int  result   =  session.insert("com.yc.dao.mapper.userMapper.addUser", user  );
             if(result >=1){
            	 session.commit();
             }else{
            	 session.rollback();
             }
		return result;
	}
}
