package com.smart.dao;

import com.smart.domain.ViewPoint;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.unitils.spring.annotation.SpringBeanByName;

import static org.testng.Assert.assertNotNull;


public class ViewPointDaoTest extends BaseDaoTest {

    @SpringBeanByName
    private ViewPointDao viewPointDao;

    @Test
    public void testAddViewPoint() throws Throwable {
        ViewPoint viewPoint = new ViewPoint();
        viewPoint.setPointId(2);
        //①获取图片资源
        ClassPathResource res = new ClassPathResource("temp.jpg");
        //②读取图片文件的数据
        byte[] mockImg = FileCopyUtils.copyToByteArray(res.getFile());
        viewPoint.setImgFile(mockImg);
        viewPoint.setDescription("测试景区介绍");
        viewPointDao.addViewPoint(viewPoint);
    }



    @Test
    public void testGetViewPointRowSet() {
        SqlRowSet srs = viewPointDao.getViewPointRowSet(1);
        assertNotNull(srs);
        //①这时，数据连接已经断开
        while (srs.next()) {
            assertNotNull(srs.getString("point_id"));
            assertNotNull(srs.getString("point_name"));
        }
    }



}
