package com.smart.dao.hibernate;

import java.util.List;


import com.smart.domain.CommentLog;
import com.smart.domain.User;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.*;

import static org.testng.Assert.*;

@ContextConfiguration(locations = {"classpath:applicationContext-hbt.xml"})
@TransactionConfiguration
@Transactional
public class XmlHibernateDaoTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private ViewSpaceHibernateDao viewSpaceDao;

    @Autowired
    private ViewPointHibernateDao viewPointDao;

    @Autowired
    private CommentLogHibernateDao commentLogDao;

    /**
     * JDBC4(需要JAVA SE6以上版本)提供对大对象类型支持的增强，如CLOB\BOLB,
     * 因此无需采用Spring提供的大对象类型转换，如ClobStringType、BlobByteArrayType，直接使用即可。
     * 在Spring3.0之后的版本提供对Hibernate4支持中，已经去掉大对象类型类。
     *
     * @throws Throwable
     */
    @Test
    public void testAddViewPoint() throws Throwable {
        ViewSpace viewSpace = new ViewSpace();
        viewSpace.setSpaceId(2);

        ViewPoint viewPoint = new ViewPoint();
        viewPoint.setViewSpace(viewSpace);
        viewPoint.setPointId(2);
        viewPoint.setPointName("厦门馆");
        viewPoint.setTicketPrice(100);
        Resource resource = new ClassPathResource("temp.jpg");
        byte[] imgFile = FileCopyUtils.copyToByteArray(resource.getFile());
        viewPoint.setDescription("厦门馆");// Clob 大字段
        viewPoint.setImgFile(imgFile);  //Bolb  大字段
        viewPointDao.addViewPoint(viewPoint);
    }

    @Test
    public void testAddViewSpace() {
        ViewSpace viewSpace = new ViewSpace();
        User user = new User();
        user.setUserId(1);
        viewSpace.setAddress("厦门集美");
        viewSpace.setSpaceId(2);
        viewSpace.setUser(user);
        viewSpace.setSpaceName("园博园");
        viewSpace.setDescription("厦门集美园博园");
        viewSpaceDao.addViewSpace(viewSpace);
    }

    @Test
    public void testFindViewSpaceByName() {
        List<ViewSpace> viewSpaces = viewSpaceDao.findViewSpaceByName("鼓浪屿");
        assertTrue(viewSpaces.size() > 0);
    }

    @Test
    public void testFindViewPointByName() {
        List<ViewPoint> viewPoints = viewPointDao.findViewPointByName("日光岩");
        assertTrue(viewPoints.size() > 0);
    }

}
