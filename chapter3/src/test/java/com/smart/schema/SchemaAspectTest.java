package com.smart.schema;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.NaiveWaiter;
import com.smart.Seller;
import com.smart.SmartSeller;
import com.smart.Waiter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class SchemaAspectTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/schema/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }
    @Test
	public void SchemaAspect() {
		Waiter naiveWaiter = (Waiter) ctx.getBean("naiveWaiter");
		Waiter naughtyWaiter = (Waiter) ctx.getBean("naughtyWaiter");	
		Seller seller = (Seller) ctx.getBean("seller");
//		naiveWaiter.greetTo("John");
//		naughtyWaiter.greetTo("Tom");
		
		//后置增强
//		((SmartSeller)seller).sell("Beer","John");
		
		//环境增强
//		naiveWaiter.serveTo("John");
		
		//抛出异常增强
//		((SmartSeller)seller).checkBill(1);
		
		//final增强
//		naiveWaiter.greetTo("John");
		
		//引入增强
//		((Seller)naiveWaiter).sell("Beer","John");
//		((NaiveWaiter)naiveWaiter).smile("John", 2);
		
		//advisor
		naiveWaiter.greetTo("John");
		
	}
}
