package com.smart.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.smart.Car;

public class XmlApplicationContextTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/context/*.xml");
		Car car1 = ctx.getBean("car",Car.class);
//		ctx = new FileSystemXmlApplicationContext("D:/masterSpring/chapter3/src/com/smart/context/*.xml");
//		Car car2 = ctx.getBean("car",Car.class);
	}
}
