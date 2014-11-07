package com.smart.auto;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutowire{
	public ApplicationContext factory = null;

	private static String[] CONFIG_FILES = { "com/smart/auto/beans.xml" };
	@BeforeClass
    public void setUp() throws Exception {
		factory = new ClassPathXmlApplicationContext(CONFIG_FILES);
		
	}
	@Test
	public void testAutoByName(){
		Boss boss = (Boss) factory.getBean("boss");
		System.out.println("boss:"+boss);
	}
	
}
