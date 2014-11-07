package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class RegexpAdvisorTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/advisor/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }

    @Test
	public  void regexpAdvisor() {
		Waiter waiter = (Waiter)ctx.getBean("waiter1");
		waiter.greetTo("John");
		waiter.serveTo("John");
	}
}
