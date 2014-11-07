package com.smart.attr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class ParentContainerBeanTest {
    public ApplicationContext parentCtx = null;
    public ApplicationContext ctx = null;
    private static String[] PARENT_CONFIG_FILES = {"com/smart/attr/beans1.xml"};
    private static String[] CONFIG_FILES = {"com/smart/attr/beans2.xml"};

    @BeforeClass
    public void setUp() throws Exception {
        parentCtx = new ClassPathXmlApplicationContext(PARENT_CONFIG_FILES);
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES, parentCtx);
    }

    @Test
    public void testInheritedContext() {
        Boss boss = (Boss) ctx.getBean("boss");
        assertNotNull(boss);
        assertNotNull(boss.getCar()); //Car定义在父容器中
    }

    @AfterClass
    public void testDestroy() {
        ((ClassPathXmlApplicationContext) ctx).destroy();
    }

}
