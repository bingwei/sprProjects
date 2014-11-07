package com.smart.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.smart.Car;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class BeanFactoryTest {
    public BeanFactory ctx = null;
    private static String CONFIG_FILES = "classpath:com/smart/beanfactory/beans.xm";

    @BeforeClass
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext(CONFIG_FILES);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource(CONFIG_FILES);
        ctx = new XmlBeanFactory(res);
    }

    @Test
    public void testGetBean() {
        assertNull(ctx);
        Car car = ctx.getBean("car", Car.class);
        assertNotNull(car);
        car.introduce();
    }

    @AfterClass
    public void testDestroy() {
        ((ClassPathXmlApplicationContext) ctx).destroy();
    }
}
