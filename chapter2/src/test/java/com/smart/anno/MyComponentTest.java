package com.smart.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;

import static org.testng.Assert.*;

public class MyComponentTest {
    public ApplicationContext ctx = null;
    private static String[] CONFIG_FILES = {"com/smart/anno/mycomponent.xml"};

    @BeforeClass
    public void init() throws Exception {
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
    }

    @Test
    public void testAutowiredPlugins() {
        MyComponent myComponent = ctx.getBean(MyComponent.class);
        assertNotNull(myComponent);
        assertNotNull(myComponent.getPlugins());
        assertEquals(myComponent.getPlugins().size(), 2);
    }

    @AfterClass
    public void testDestroy() {
        ((ClassPathXmlApplicationContext) ctx).destroy();
    }
}
