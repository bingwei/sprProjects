package com.smart.beanfactory;
import com.smart.Car;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class BeanLifeCycleTest {

    @Test
    public  void testLifeCycle() {
        Resource res = new ClassPathResource("com/smart/beanfactory/beans.xml");
        //BeanFactory bf = new XmlBeanFactory(res);  //已过弃

        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);

        assertNotNull(bf);

        (new MyBeanFactoryPostProcessor()).postProcessBeanFactory(bf);
        //InstantiationAwareBeanPostProcessor
        ( bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //BeanPostProcessor
        (bf).addBeanPostProcessor(new MyBeanPostProcessor());
        Car car1 = (Car) bf.getBean("car");
        car1.introduce();
        car1.setColor("红色");
        Car car2 = bf.getBean("car", Car.class);
        car2.introduce();
        (bf).destroySingletons();
    }




}
