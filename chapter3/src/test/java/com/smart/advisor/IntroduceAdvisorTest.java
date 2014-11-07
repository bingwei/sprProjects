package com.smart.advisor;

import com.smart.introduce.ViewSpaceService;
import com.smart.introduce.Monitorable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class IntroduceAdvisorTest {
    private String configPath ="";
    private ApplicationContext ctx =null;

    @BeforeClass
    public void init(){
        configPath = "com/smart/advisor/beans.xml";
        ctx = new ClassPathXmlApplicationContext(configPath);
    }

    @Test
	public  void introduceAdvisor() {
        ViewSpaceService viewSpaceService = (ViewSpaceService)ctx.getBean("viewSpaceService");
        viewSpaceService.deleteViewSpace(10);
        Monitorable moniterable = (Monitorable)viewSpaceService;
        moniterable.setMonitorActive(true);
        viewSpaceService.deleteViewSpace(10);
	}
}
