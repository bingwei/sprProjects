package com.smart.conf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;

public class JavaConfigTest {

    @Test
    public  void javaConfig() {

        //1.通过构造函数加载配置类
        ApplicationContext ctx1 = new AnnotationConfigApplicationContext(AppConf.class);

        //2.通过编码方式注册配置类
        AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext();
        ctx2.register(DaoConfig.class);
        ctx2.register(ServiceConfig.class);
        ctx2.refresh();

        //3.通过XML组装@Configuration配置类所提供的配置信息
        ApplicationContext ctx3 = new ClassPathXmlApplicationContext("com/smart/conf/beans2.xml");

        //4.通过@Configuration组装XML配置所提供的配置信息
        ApplicationContext ctx4 = new AnnotationConfigApplicationContext(LogonAppConfig.class);

        //5.@Configuration的配置类相互引用
        ApplicationContext ctx5 = new AnnotationConfigApplicationContext(DaoConfig.class, ServiceConfig.class);
        LogonService logonService = ctx5.getBean(LogonService.class);
        System.out.println((logonService.getLogDao() != null));
    }
}
