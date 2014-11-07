package com.smart.dao;

import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByName;

import static org.testng.Assert.assertNotNull;

public class CommentLogDaoTest extends BaseDaoTest{
	
	@SpringBeanByName
	private CommentLogDao commentLogDao;
	
    @Test
    public void test(){
        assertNotNull(commentLogDao);
    }

}
