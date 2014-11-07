package com.smart.service;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
@SpringApplicationContext( {"viewspace-service.xml", "viewspace-dao.xml" })
public class BaseServiceTest extends UnitilsTestNG {
	@SpringBean("hibernateTemplate")
	public  HibernateTemplate hibernateTemplate;

}
