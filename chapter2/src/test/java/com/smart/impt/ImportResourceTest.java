package com.smart.impt;

import com.smart.fb.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class ImportResourceTest {

     @Test
	  public void load() {
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:com/smart/impt/beans2.xml");
		 Car car = ctx.getBean("car1",Car.class);
		 assertNotNull(car);
	}
}
