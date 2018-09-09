package com.yc.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yc.bean.Student;
public class TestMybatis {
	
	static SqlSessionFactory factory = null;

	 //在每个test方法调用之前先调用一次
	@Before  
	public void testInitSetup() {
		String resource = "Configuration.xml";
		factory = null;
		try {
			Reader reader  = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSessionFactory(){
		System.out.println("factory:" + factory);
		Assert.assertNotNull(factory);
	}
	
	
	@Test
	public void testSelectOne() {
		 
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	Student student =  session.selectOne( "com.yc.dao.mapper.studentMapper.selectOneStudent", 1);
	   	  System.out.println(  student );
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}

		Assert.assertNotNull(factory);
	}

	
	@Test
	public void testAdd() {
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	int  result =  session.insert( "com.yc.dao.mapper.studentMapper.addStudent", "adeline");
	   	  if(result>=1){
	   		 session.commit();  
	   		  System.out.println(  " insert success");
	   	  }else{
	   		  session.rollback();
	   		  System.out.println(" insert falure");
	   	  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
		Assert.assertNotNull(factory);
	}
	
	@Test
	public void testUpdateStudent() {
		SqlSession session = null ;
		try {
			session = factory.openSession();
			Student s = new Student() ;
			s.setSid(5);
			s.setSname("bob");
	    	int  result =  session.update( "com.yc.dao.mapper.studentMapper.updateStudent", s);
			if(result>=1){
		   		 session.commit();  
		   		  System.out.println(  " update success");
		   	  }else{
		   		  session.rollback();
		   		  System.out.println(" update falure");
		   	  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
		Assert.assertNotNull(factory);
	}
	
	@Test
	public void testDelStudent() {
		SqlSession session = null ;
		try {
			session = factory.openSession();
			Student s = new Student() ;
			 
	    	int  result =  session.delete( "com.yc.dao.mapper.studentMapper.delStudent", 5);
			if(result>=1){
		   		 session.commit();  
		   		  System.out.println(  " delete success");
		   	  }else{
		   		  session.rollback();
		   		  System.out.println(" delete falure");
		   	  }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
		Assert.assertNotNull(factory);
	}
}
