package com.smart.aspectj.advanced;

import com.smart.NaiveWaiter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.SmartSeller;
import com.smart.Waiter;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class AdvancedTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/aspectj/advanced/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }

    @Test(expectedExceptions =RuntimeException.class)
	public  void advance() {
		Waiter naiveWaiter = (Waiter) ctx.getBean("naiveWaiter");
		Waiter naughtyWaiter = (Waiter) ctx.getBean("naughtyWaiter");
		naiveWaiter.greetTo("John");
		naiveWaiter.serveTo("John");
		naughtyWaiter.greetTo("Tom");
		naughtyWaiter.serveTo("Tom");
		
        //--通过joinPoint接口访问连接点上下文信息
       	naiveWaiter.greetTo("John");
		
		//--绑定连接点参数
		(naiveWaiter).smile("John",2);
		
		//--绑定代理对象
		naiveWaiter.greetTo("John");
		
		//--绑定类注解
      	(naiveWaiter).greetTo("John");

		//绑定返回值
		SmartSeller seller = (SmartSeller) ctx.getBean("seller");
    	seller.sell("Beer","John");
		
		//绑定异常
	    seller = (SmartSeller) ctx.getBean("seller");
		seller.checkBill(2);
    	seller.checkBill(1);

	}
}
