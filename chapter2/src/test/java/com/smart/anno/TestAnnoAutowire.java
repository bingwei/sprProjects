package com.smart.anno;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestAnnoAutowire{
	public ApplicationContext factory = null;
	private static String[] CONFIG_FILES = { "com/smart/anno/beans.xml" };
	
    @BeforeClass
    public void init() throws Exception {
		factory = new ClassPathXmlApplicationContext(CONFIG_FILES);
	}

    @Test
	public void testAutoByName(){
		Boss boss = (Boss) factory.getBean("boss");

		/*System.out.println("boss.office:"+boss.getOffice());
		System.out.println("boss.title:"+boss.getTitle());
		System.out.println("boss.car:"+boss.getCar());*/
	}
	
}
