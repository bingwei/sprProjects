package com.smart.context;

import com.smart.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.*;
import static  org.testng.Assert.*;

public class AnnotationApplicationContextTest {

    @Test
    public  void load() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
        Car car = ctx.getBean("car", Car.class);
        assertNotNull(car);
    }
}
