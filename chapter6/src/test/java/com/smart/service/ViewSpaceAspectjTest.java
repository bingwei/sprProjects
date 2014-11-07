package com.smart.service;


import com.smart.domain.User;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.testng.annotations.*;
import org.unitils.spring.annotation.SpringBeanByType;

import static org.testng.Assert.*;

@SpringApplicationContext({"classpath:applicationContext-aspectj.xml"})
public class ViewSpaceAspectjTest extends UnitilsTestNG {

    @SpringBeanByType
    private IViewSpace viewSpaceService;

    @Test
    public void addViewSpace() throws Throwable {
        System.out.println("begin........");
        ViewSpace vs = new ViewSpace();
        vs.setSpaceName("space name!");
        User u = new User();
        u.setUserId(1);
        vs.setUser(u);
        viewSpaceService.addViewSpace(vs);
        System.out.println("end........");
    }
}
