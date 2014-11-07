package com.smart.service;

import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext( {"classpath:applicationContext-multi.xml"})
public class MultiViewSpaceTxTest extends UnitilsTestNG {


    @SpringBean("multiViewSpaceService")
    private  MultiViewSpaceService viewSpaceService;

    @Test
	public  void test() throws Throwable{
		System.out.println("begin");
        viewSpaceService.addViewPoint(null);
		System.out.println("end");

		System.out.println("begin");
		viewSpaceService.updateViewSpace(null);
		System.out.println("end");
    	
    }
}
