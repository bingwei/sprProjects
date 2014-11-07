package com.smart.introduce;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IntroduceTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/introduce/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }
    @Test
    public void testIntroduce() {
        ViewSpaceService viewSpaceService = (ViewSpaceService) ctx.getBean("viewSpaceService");

        viewSpaceService.deleteViewPoint(10);
        viewSpaceService.deleteViewPoint(1022);

        Monitorable moniterable = (Monitorable) viewSpaceService;
        moniterable.setMonitorActive(true);

        viewSpaceService.deleteViewSpace(10);
        viewSpaceService.deleteViewSpace(1022);
    }

}
