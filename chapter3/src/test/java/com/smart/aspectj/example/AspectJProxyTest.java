package com.smart.aspectj.example;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import com.smart.NaiveWaiter;
import com.smart.Waiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class AspectJProxyTest {
    AspectJProxyFactory factory =null;

    @BeforeClass
    public void init(){
        Waiter target = new NaiveWaiter();
        factory = new AspectJProxyFactory();
        factory.setTarget(target);
        factory.addAspect(PreGreetingAspect.class);
    }

    @Test
	public  void aspectJProxy() {
		Waiter proxy = factory.getProxy();
		proxy.greetTo("John");
		proxy.serveTo("John");
	}
}
