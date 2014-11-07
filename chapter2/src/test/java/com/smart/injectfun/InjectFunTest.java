package com.smart.injectfun;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectFunTest {
	public ApplicationContext factory = null;

	private static String[] CONFIG_FILES = { "classpath:com/smart/injectfun/beans.xml" };

    @BeforeClass
    public void setUp() throws Exception {
		factory = new ClassPathXmlApplicationContext(CONFIG_FILES);
		
	}
	@Test
	public void testLookup(){
		MagicBoss mboss = (MagicBoss) factory.getBean("magicBoss");
		assertNotSame(mboss.getCar(),mboss.getCar());
	}

    @Test
	public void testReplace(){
		MagicBoss mboss = (MagicBoss) factory.getBean("boss1");
		assertEquals(mboss.getCar().getBrand(),"美人豹");
	}
	
}
