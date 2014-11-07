package com.smart.dao;

import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByName;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class ViewSpaceOODaoTest extends BaseDaoTest {

    @SpringBeanByName
    private ViewSpaceOODao viewSpaceOODao;


    @BeforeMethod
    public void init(){
        assertNotNull(viewSpaceOODao);
    }

    @Test
    public void addViewSpace(){
        ViewSpace space = new ViewSpace();
        space.setSpaceName("鼓浪屿");
        space.setAddress("厦门");
        space.setDescription("厦门鼓浪屿");
        viewSpaceOODao.addViewSpace(space);
    }


    @Test
    public void getViewSpaceNum(){
       int count =  viewSpaceOODao.getViewSpaceNum();
        assertTrue(count>0);
    }



}
