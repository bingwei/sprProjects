package com.smart.service;

import com.smart.domain.User;
import com.smart.domain.ViewSpace;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;
import org.testng.annotations.*;
import org.unitils.spring.annotation.SpringBeanByType;
import static org.testng.Assert.*;

@SpringApplicationContext({"classpath:applicationContext-pfb.xml"})
public class ViewSpacePfbTest extends UnitilsTestNG {

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
