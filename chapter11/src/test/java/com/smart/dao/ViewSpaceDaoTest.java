package com.smart.dao;
import java.util.List;

import com.smart.domain.User;
import com.smart.domain.ViewSpace;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * ViewSpaceDao 测试
 */
public class ViewSpaceDaoTest extends BaseDaoTest {

	@SpringBean("viewSpaceDao")
	private ViewSpaceDao viewSpaceDao;
	
	@Test
	@ExpectedDataSet("ViewSpaceDao.ExpectedViewSpaces.xls")
	public void save()throws Exception {
	    List<ViewSpace> viewSpaces = XlsDataSetBeanFactory.createBeans(ViewSpaceDaoTest.class, "ViewSpaceDao.SaveViewSpaces.xls", "t_view_space", ViewSpace.class);
	    User user = new User();
        user.setUserId(1);
        for(ViewSpace viewSpace:viewSpaces){
            viewSpace.setUser(user);
	    	viewSpaceDao.save(viewSpace);
	    }
	}

    @Test
    @DataSet("ViewSpaceDao.ViewSpaces.xls")
    @ExpectedDataSet("ViewSpaceDao.ExpectedViewSpaces.xls")
    public void remove()throws Exception {
        List<ViewSpace> viewSpaces = XlsDataSetBeanFactory.createBeans(ViewSpaceDaoTest.class, "ViewSpaceDao.RemoveViewSpaces.xls", "t_view_space", ViewSpace.class);
        for(ViewSpace viewSpace:viewSpaces){
            viewSpaceDao.remove(viewSpace);
        }
        viewSpaceDao.getHibernateTemplate().clear();
    }

    @Test
    @DataSet("ViewSpaceDao.ViewSpaces.xls")
    public void queryByName() throws Exception {
       List viewSpaces = viewSpaceDao.queryByName("鼓浪屿");
       assertNotNull(viewSpaces);
       assertEquals(viewSpaces.size(),1);
    }

}
