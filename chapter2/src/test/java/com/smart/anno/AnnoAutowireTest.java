package com.smart.anno;

import static org.testng.Assert.*;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;

public class AnnoAutowireTest {
    public ApplicationContext ctx = null;
    private static String[] CONFIG_FILES = {"com/smart/anno/beans.xml"};

    @BeforeClass
    public void init() throws Exception {
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }

    @Test
    public void testAutoByName() {
        Boss boss = (Boss) ctx.getBean("boss");
        assertNotNull(boss);
    }

    @Test
    public void testAutoByType() {
        LogonService logonService = ctx.getBean(LogonService.class);
        assertNotNull(logonService);
    }

    @Test
    public void testPrototypeBean() {
        Car car1 = ctx.getBean(Car.class);
        Car car2 = ctx.getBean(Car.class);
        System.out.println(car1 != car2);
    }

    @AfterClass
    public void testDestroy() {
        ((ClassPathXmlApplicationContext) ctx).destroy();
    }
}
