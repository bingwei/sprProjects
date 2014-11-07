package com.smart.aspectj.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import com.smart.Waiter;

public class AspectConfigTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/aspectj/example/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }

    @Test
	public  void aspectConfig() {
		Waiter waiter = (Waiter)ctx.getBean("waiter");
		waiter.greetTo("John");
		waiter.serveTo("John");
		waiter.toString();
	}
}
