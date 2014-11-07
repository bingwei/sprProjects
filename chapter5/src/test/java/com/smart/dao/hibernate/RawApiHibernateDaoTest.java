package com.smart.dao.hibernate;

import java.util.List;

import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.smart.dao.hibraw.ViewSpaceHibernateDao;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@ContextConfiguration(locations = {"classpath:applicationContext-hbt-raw.xml"})
@TransactionConfiguration
@Transactional
public class RawApiHibernateDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ViewSpaceHibernateDao viewSpaceDao;

    @Test
    public void testAllMethod() {
        ViewSpace viewSpace = viewSpaceDao.getViewSpace(1);
        assertNotNull(viewSpace);
        List<ViewSpace> viewSpaces = viewSpaceDao.findViewSpaceByName("鼓浪屿");
        assertTrue(viewSpaces.size() > 0);
    }

}
