package com.yc.biz.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.yc.bean.Sysuser;

public class SysuserImplTest {

	@Test
	public void testGetAll() {
		 SysuserImpl si = new SysuserImpl();
		 List<Sysuser> user = si.getAll();
	    if(user != null ){
	    	System.out.println(user);
	    }
	}

	
	@Test
	public void testGetAUSer() {
		 SysuserImpl si = new SysuserImpl();
		  Sysuser  user = si.getAUser(1);
	    if(user != null ){
	    	System.out.println(user);
	    }
	}
	
	@Test
	public void testUpdate() {
		 SysuserImpl si = new SysuserImpl();
		 Sysuser  user = new Sysuser(1,"adelinex", "adelinex");
		 si.update(user);
	  
	}
}
