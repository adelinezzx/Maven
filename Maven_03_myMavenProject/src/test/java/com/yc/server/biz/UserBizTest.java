package com.yc.server.biz;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yc.bean.Users;

public class UserBizTest {

	
	static SqlSessionFactory factory = null;

	// 在每个test方法调用之前先调用一次
	@Before
	public void testInitSetup() {
		String resource = "Configuration.xml";
		factory = null;
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSessionFactory() {
		System.out.println("factory:" + factory);
		Assert.assertNotNull(factory);
	}

	@Test
	public void testTestLogin() {
		 Users  user = new Users("cindy","123");
		 UserBiz ub = new UserBiz() ;
		 Users u = ub.testLogin(user);
		 System.out.println(u);
		Assert.assertNotNull(u);
		 
	}
	@Test
	public void testAddUser() {
		Users user = new Users("cindy","123","18745625455","123@qq.com");
		UserBiz ub = new UserBiz() ;
		int result = ub.addUser(user);
		if(result >0) {
			System.out.println("success ");
		}else{
			System.out.println("failure ");
		}
		//Assert.assertEquals(1, result);;
	}
	
	 
}
