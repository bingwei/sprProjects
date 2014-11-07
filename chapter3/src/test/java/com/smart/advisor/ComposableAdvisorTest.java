package com.smart.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;
public class ComposableAdvisorTest {

    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/advisor/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }

    @Test
	public  void composableAdvisor() {
		Waiter waiter = (Waiter) ctx.getBean("waiter4");
		WaiterDelegate wd = new WaiterDelegate();
        wd.setWaiter(waiter);
		waiter.serveTo("Peter");
		waiter.greetTo("Peter");
		wd.service("Peter");
	}
}
