package com.smart.service;

import com.smart.domain.User;
import com.smart.domain.ViewSpace;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;
import org.testng.annotations.*;
import static org.testng.Assert.*;

@SpringApplicationContext({"classpath:applicationContext-anno.xml"})
public class ViewSpaceAnnoTest extends UnitilsTestNG {
    @SpringBeanByType
    private IViewSpace viewSpaceService;

    @Test
    public void test() throws Throwable {
        System.out.println("begin........");
        ViewSpace vs = new ViewSpace();
        User u = new User();
        u.setUserId(1);
        vs.setSpaceName("space name!");
        vs.setUser(u);
        viewSpaceService.addViewSpace(vs);
        assertTrue(vs.getSpaceId()>0);
        System.out.println("end........");
    }
}
