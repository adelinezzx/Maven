package com.yc.mybatis;

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

import com.yc.bean.Project;
import com.yc.bean.Student;

public class TestCompany {

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
	public void testSelectProject() {
		 
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	List<Project>  student =  session.selectList( "com.yc.dao.mapper.projectMapper.selectProject", 4);
	   	  System.out.println(  student );
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}

		Assert.assertNotNull(factory);
	}
	
	@Test
	public void testSelectAllProject() {
		 
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	List<Project>  student =  session.selectList( "com.yc.dao.mapper.projectMapper.selectAllProject");
	   	  System.out.println(  student );
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}

		Assert.assertNotNull(factory);
	}
}
