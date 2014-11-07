package com.smart.dao;

import com.smart.domain.User;
import com.smart.domain.ViewPoint;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import java.util.*;
/**
 * ViewPointDao测试
 */
public class ViewPointTest extends BaseDaoTest {

	@SpringBean("viewPointDao")
	private ViewPointDao viewPointDao;
    // 验证数据库保存的正确性
    @Test
    @DataSet("ViewPointDao.EmptyViewPoints.xls")
    @ExpectedDataSet("ViewPointDao.ExpectedViewPoints.xls")// 准备验证数据
    public void saveUser()throws Exception  {
        //通过XlsDataSetBeanFactory数据集绑定工厂创建测试实体
        List<ViewPoint> viewPoints  = XlsDataSetBeanFactory.createBeans(ViewPointTest.class, "ViewPointDao.ViewPoints.xls", "t_view_point", ViewPoint.class);
        assertNotNull(viewPoints);
        for(ViewPoint viewPoint:viewPoints)
             viewPointDao.save(viewPoint);  //执行用户信息更新操作
    }
}
