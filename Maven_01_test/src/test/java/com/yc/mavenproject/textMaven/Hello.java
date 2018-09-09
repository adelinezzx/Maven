package com.yc.mavenproject.textMaven;

 
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Hello  extends TestCase{
	
	 @Test 
	public void testHello(){
		System.out.println("test 1");
		Assert.assertEquals(true, true);
	}
	

}
