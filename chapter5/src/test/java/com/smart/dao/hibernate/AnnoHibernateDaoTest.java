package com.smart.dao.hibernate;

import java.util.List;


import com.smart.domain.CommentLog;
import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.*;

import static org.testng.Assert.*;


@ContextConfiguration(locations = {"classpath:applicationContext-hbt-anno.xml"})
@TransactionConfiguration
@Transactional
public class AnnoHibernateDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private ViewSpaceHibernateDao viewSpaceDao;

    @Autowired
    private CommentLogHibernateDao commentLogDao;


    @Test
    @Rollback(value = false)
    public void testAddCommentLog() throws Throwable {
        ViewSpace viewSpace = new ViewSpace();
        viewSpace.setSpaceId(1);
        CommentLog commentLog = new CommentLog();
        commentLog.setLogId(10);
        commentLog.setIp("192.168.1.1");

        //Resource resource = new ClassPathResource("temp.jpg");
        //byte[] imgFile =FileCopyUtils.copyToByteArray(resource.getFile());

        commentLog.setViewSpace(viewSpace);
        commentLogDao.addCommentLog(commentLog);

    }

    @Test
    public void getViewSpaceNum() {
        long num = viewSpaceDao.getViewSpaceNum();
        assertTrue(num > 0);
    }

    @Test
    public void testFindForumByName() {
        List<ViewSpace> viewSpaces = viewSpaceDao.findViewSpaceByName("鼓浪屿");
        assertTrue(viewSpaces.size() > 0);
    }

}
