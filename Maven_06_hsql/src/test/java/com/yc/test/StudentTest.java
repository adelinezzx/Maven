package com.yc.test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yc.bean.Classes;
import com.yc.bean.Student;

public class StudentTest {
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
		 Student s = new Student( );
		 s.setStuName("smith");
		 s.setStuSex("男");
		 s.setStuBirthday( new Date());
		 
		 Classes classes = new Classes() ;
		 classes.setClassId(3001);
		 
		 s.setClasses(classes);
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 int student =  session.insert( "com.yc.dao.mapper.studentMapper.add",s);
	   	    System.out.println(  student );
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	
	@Test
	public void testSelectStuByIf() {
		 Student s = new Student( );
		// s.setStuName("smith");
		 s.setStuSex("男");
		 s.setStuBirthday( new Date());
		 
		 Classes classes = new Classes() ;
		 classes.setClassId(3001);
		 
		 s.setClasses(classes);
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 List<Student>  student =  session.selectList( "com.yc.dao.mapper.studentMapper.selectStuByIf",s);
	   	    System.out.println(  student );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	
	 
	
	@Test
	public void testUpdateStu() {
		 Student s = new Student( );
		 s.setStuId(5);
		 s.setStuName("smith2");
		 s.setStuSex("女");
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 int student =  session.update( "com.yc.dao.mapper.studentMapper.updateStu",s);
	   	    System.out.println(  student );
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	
	 
	
	@Test
	public void testSelectStuByTrim() {
		 Student s = new Student( );
		// s.setStuName("smith");
		 s.setStuSex("男");
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 List<Student>  student =  session.selectList( "com.yc.dao.mapper.studentMapper.selectStuByTrim",s);
	   	    System.out.println(  student );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	
	
	@Test
	public void testUpdateStu02() {
		 Student s = new Student( );
		 s.setStuId(5);
		 s.setStuName("smith2");
		 s.setStuSex("女");
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 int student =  session.update( "com.yc.dao.mapper.studentMapper.updateStu02",s);
	   	    System.out.println(  student );
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	
	
	@Test
	public void testGetStu03() {
		 Student s = new Student( );
		 s.setStuName("smith");
		 //s.setStuSex("男");
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 List<Student>  student =  session.selectList( "com.yc.dao.mapper.studentMapper.getStu03",s);
	   	    System.out.println(  student );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	/**
	 * 查询的一级缓存
	 */
	@Test
	public void testFind() {
		//		  session級的緩存是跟session有關係，即發出同一條語句時，只用發一次語句
		SqlSession session = null ;
		try {
			session = factory.openSession();
	    	 List<Student>  student =  session.selectList( "com.yc.dao.mapper.studentMapper.findAllStudent");
	   	    System.out.println(  student );
	   	    
	   	 List<Student>  student2 =  session.selectList( "com.yc.dao.mapper.studentMapper.findAllStudent");
	   	    System.out.println(  student2 );
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		  session.close();
		}
	}
	
}
