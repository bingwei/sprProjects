package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class StaticMethodAdvisorTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
         configPath = "com/smart/advisor/beans.xml";
         ctx = new ClassPathXmlApplicationContext(configPath);
    }

    @Test
	public  void staticMethod() {
		Waiter waiter = (Waiter)ctx.getBean("waiter");
		Seller seller = (Seller)ctx.getBean("seller");
		waiter.greetTo("John");
		waiter.serveTo("John");
		seller.greetTo("John");	
	}
}
