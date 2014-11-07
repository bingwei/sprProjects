package com.smart.attr;

import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAttrDITest {

    public ApplicationContext ctx = null;

    private static String[] CONFIG_FILES = {"com/smart/attr/beans.xml"};

    @BeforeClass
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }

    @Test
    public void testBeanRetrieveCar() {
        Car car = (Car) ctx.getBean("car");
        assertNotNull(car);
        System.out.println("" + car);
    }

    @Test
    public void testBeanRetrieveBoss() {
        Boss boss1 = (Boss) ctx.getBean("boss1");
        Boss boss2 = (Boss) ctx.getBean("boss2");
        assertNotNull(boss1);
        assertNotNull(boss2);
        assertNotNull(boss1.getCar());
        assertNotNull(boss2.getCar());
        //assertTrue(boss1.getCar() == boss2.getCar());
    }

    @Test
    public void testBeanRetrieveBoss3() {
        Boss boss1 = (Boss) ctx.getBean("boss1");
        assertNotNull(boss1.getCar().getBrand());
    }

    @Test
    public void testBeanRetrieveBoss1() {
        Boss boss1 = (Boss) ctx.getBean("boss1");
        assertNotNull(boss1);
        System.out.println("boss1:" + boss1);
    }

    @Test
    public void testBeanRetrieveChildBoss() {
        Boss childBoss = (Boss) ctx.getBean("childBoss");
        assertNotNull(childBoss);
        System.out.println("childBoss:" + childBoss);
    }

    @Test
    public void testCascadeAttr() {
        SportsCar sportsCar = (SportsCar) ctx.getBean("sportsCar");
        System.out.println(sportsCar.getBrand());
    }


    @AfterClass
    public void testDestroy() {
        ((ClassPathXmlApplicationContext) ctx).destroy();
    }

}
