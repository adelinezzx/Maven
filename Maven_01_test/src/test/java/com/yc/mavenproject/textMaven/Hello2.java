package com.yc.mavenproject.textMaven;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Hello2   extends TestCase{
	@Test
	public void testHello2(){
		System.out.println("test 测试");
		Assert.assertEquals(true, true);
	}
}
