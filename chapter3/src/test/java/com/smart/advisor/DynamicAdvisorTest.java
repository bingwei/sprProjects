package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;
public class DynamicAdvisorTest {

    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/advisor/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }


    @Test
	public  void  dynamicAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter2");
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");		
		waiter.serveTo("Peter");
		waiter.greetTo("John");
	}
}
