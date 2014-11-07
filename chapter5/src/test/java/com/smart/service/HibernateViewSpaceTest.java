package com.smart.service;

import com.smart.domain.User;
import com.smart.domain.ViewSpace;
import com.smart.service.hibernate.ViewSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@ContextConfiguration(locations = {"classpath:applicationContext-hbt.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class HibernateViewSpaceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ViewSpaceService viewSpaceSerive;

    private User user;

    @BeforeClass
    public void init() {
        user = new User();
        user.setUserId(1);
        user.setUserName("admin");
    }

    @Test
    @Rollback(value = false)
    public void testHibernateInit() {
        ViewSpace viewSpace = new ViewSpace();
        viewSpace.setUser(user);
        viewSpace.setSpaceId(2);
        viewSpace.setSpaceName("鼓浪屿");
        viewSpace.setDescription("鼓浪屿");
        viewSpaceSerive.addViewSpace(viewSpace);
    }

}
