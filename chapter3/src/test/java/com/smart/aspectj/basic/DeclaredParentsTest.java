package com.smart.aspectj.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.smart.Seller;
import com.smart.Waiter;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class DeclaredParentsTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/aspectj/basic/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }
    @Test
	public  void  declaredParents() {
		Waiter waiter = (Waiter)ctx.getBean("waiter");
		waiter.greetTo("John");
		Seller seller = (Seller)waiter;
		seller.sell("Beer", "John");
	}
}
