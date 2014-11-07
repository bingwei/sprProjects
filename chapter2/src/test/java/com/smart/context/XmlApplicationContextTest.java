package com.smart.context;

import com.smart.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.*;
import static  org.testng.Assert.*;
public class XmlApplicationContextTest {

    @Test
    public  void load() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/smart/context/beans.xml");
        Car car1 = ctx.getBean("car", Car.class);
        assertNotNull(car1);
	    ctx = new FileSystemXmlApplicationContext("D:/actionSpring/chapter/chapter2/src/main/resources/com/smart/context/beans.xml");
	    Car car2 = ctx.getBean("car",Car.class);
        assertNotNull(car2);
    }
}
