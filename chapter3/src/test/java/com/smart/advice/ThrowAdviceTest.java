package com.smart.advice;

import com.smart.domain.ViewSpace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.*;

public class ThrowAdviceTest {


    @Test
    public void testThrowAdvice() {
        String configPath = "com/smart/advice/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ViewSpaceService viewSpaceService = (ViewSpaceService) ctx.getBean("viewSpaceService");

        try {
            boolean result = viewSpaceService.deleteViewSpace(10);
            System.out.println(".........." + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            viewSpaceService.updateViewSpace(new ViewSpace());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
