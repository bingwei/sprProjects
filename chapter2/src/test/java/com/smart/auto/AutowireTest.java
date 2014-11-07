package com.smart.auto;

import org.testng.annotations.*;

import static org.testng.Assert.*;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireTest {
    public ApplicationContext ctx = null;
    private static String[] CONFIG_FILES = {"com/smart/auto/beans.xml"};

    @BeforeClass
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }

    @Test
    public void testAutoByName() {
        Boss boss = (Boss) ctx.getBean("boss");
        assertNotNull(boss);
    }

    @Test
    public void testSingleton() {
        Car car1 = (Car) ctx.getBean("car");
        Car car2 = (Car) ctx.getBean("car");
        assertNotNull(car1);
        assertNotNull(car2);
        assertTrue(car1 == car2);
    }

    @AfterClass
    public void testDestroy() {
        ((ClassPathXmlApplicationContext) ctx).destroy();
    }
}
