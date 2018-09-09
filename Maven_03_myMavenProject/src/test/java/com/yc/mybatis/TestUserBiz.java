package com.yc.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yc.bean.Student;
import com.yc.bean.Users;

public class TestUserBiz {

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
	public void testLogin() {

		SqlSession session = null;
		Users user = new Users("cindy", "123");
		try {
			session = factory.openSession();
            String username = "cindy" ;
			user= session.selectOne("com.yc.dao.mapper.userMapper.login", "cindy");
			System.out.println(user);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		Assert.assertNotNull(factory);
	}
}
