package com.smart.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.smart.Car;

public class BeanLifeCycle {

    private  void lifeCycleInBeanFactory() {
        Resource res = new ClassPathResource("com/smart/beanfactory/beans.xml");

        //BeanFactory bf = new XmlBeanFactory(res);  //已过弃

        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);

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
