package com.smart.service;

import java.util.*;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.ExpectedException;
import com.smart.domain.CommentLog;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import com.smart.service.exception.IpCommentedException;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import static org.testng.Assert.*;
/**
 *  ViewSpaceService的测试类
 */
public class ViewSpaceServiceTest extends BaseServiceTest {
    @SpringBean("viewSpaceService")
	private ViewSpaceService viewSpaceService;
    @SpringBean("userService")
    private UserService userService;

    private int initSpaceId = 1;
    private int initPointId = 1;
    private int initUserId = 1;


    //① 在测试初始化中，消除Hibernate二级缓存，避免影响测试
    @BeforeMethod
    public void init(){
        SessionFactory sf  = hibernateTemplate.getSessionFactory();
        Map<String, CollectionMetadata> roleMap = sf.getAllCollectionMetadata();
        for (String roleName : roleMap.keySet()) {
            sf.evictCollection(roleName);
        }
        Map<String, ClassMetadata> entityMap = sf.getAllClassMetadata();
        for (String entityName : entityMap.keySet()) {
            sf.evictEntity(entityName);
        }
        sf.evictQueries();
    }


	/**
	 * 测试添加景点方法
	 */
    @Test
    @DataSet({"ViewSpaces.xls","EmptyViewPoint.xls"})
	public void testAddViewPoint()throws Exception{
        ViewPoint viewPoint = XlsDataSetBeanFactory.createBean(ViewSpaceServiceTest.class,
                "AddViewPoint.xls", "t_view_point", ViewPoint.class);
        ViewSpace viewSpace = new ViewSpace();
        viewSpace.setSpaceId(initSpaceId);
        viewPoint.setViewSpace(viewSpace);
		viewSpaceService.addViewPoint(viewPoint);
        ViewPoint viewPointDB = viewSpaceService.getFullViewPoint(viewPoint.getPointId());
        assertNotNull(viewPointDB);
        assertEquals(viewPoint.getTicketPrice(),viewPointDB.getTicketPrice());
	}

	/**
	 * 测试更新景点方法
	 */
    @Test
    @DataSet({"ViewSpaces.xls","ViewPoints.xls"})
	public void testUpdateViewPoint()throws Exception{
        ViewPoint viewPoint = viewSpaceService.getFullViewPoint(initPointId);
        assertNotNull(viewPoint);
        assertEquals(88.00f,viewPoint.getTicketPrice());
        viewPoint.setTicketPrice(98.00f);
        viewSpaceService.updateViewPoint(viewPoint);
        ViewPoint newViewPoint = viewSpaceService.getFullViewPoint(viewPoint.getPointId());
        assertNotNull(newViewPoint);
        assertEquals(98.00f,newViewPoint.getTicketPrice());
	}
	
	/**
	 * 测试添加景区的方法
	 */
    @Test
    @DataSet({"EmptyViewSpace.xls","EmptyViewPoint.xls"})
	public void testAddViewSpace()throws Exception{
        ViewSpace viewSpace = XlsDataSetBeanFactory.createBean(ViewSpaceServiceTest.class,
                "AddViewSpace.xls", "t_view_space", ViewSpace.class);
        assertNotNull(viewSpace);
        viewSpaceService.addViewSpace(viewSpace);
        ViewSpace viewSpaceDB = viewSpaceService.getFullViewSpace(viewSpace.getSpaceId());
        assertNotNull(viewSpaceDB);
        assertEquals(viewSpace,viewSpaceDB);
	}

    /**
     * 测试更新景点方法
     */
    @Test
    @DataSet({"ViewSpaces.xls","ViewPoints.xls"})
    public void testUpdateViewSpace()throws Exception{
        ViewSpace viewSpace = viewSpaceService.getFullViewSpace(initSpaceId);
        assertNotNull(viewSpace);
        assertEquals("厦门思明",viewSpace.getAddress());
        viewSpace.setAddress("厦门思明区");
        viewSpaceService.updateViewSpace(viewSpace);
        ViewSpace newViewSpace  = viewSpaceService.getFullViewSpace(viewSpace.getSpaceId());
        assertNotNull(newViewSpace);
        assertEquals("厦门思明区",newViewSpace.getAddress());
    }
    /**
     * 测试根据风景区名查询风景区的方法
     */
    @Test
    @DataSet({"ViewSpaces.xls","ViewPoints.xls"})
    public void testQueryViewSpaceByName() {
        String name = "鼓浪屿";
        List<ViewSpace> viewSpaces = viewSpaceService.queryViewSpaceByName(name);
        assertNotNull(viewSpaces);
        assertTrue(viewSpaces.size() > 0);
    }

    /**
     * 测试对景区进行评论的方法
     */
    @Test(expectedExceptions = {IpCommentedException.class})
    @DataSet({"ViewSpaces.xls","ViewPoints.xls"})
    public void testAddCommentLog() throws Throwable{
        CommentLog commentLog  = XlsDataSetBeanFactory.createBean(ViewSpaceServiceTest.class,
                "AddComment.xls", "t_comment_log", CommentLog.class);
        assertNotNull(commentLog);
        ViewSpace viewSpace = new ViewSpace();
        viewSpace.setSpaceId(initSpaceId);
        commentLog.setViewSpace(viewSpace);
        commentLog.setCommentType(CommentLog.WANT_TO_GO);
        viewSpaceService.addCommentLog(commentLog);

        //是否已经插入成功
        List<CommentLog> commentLogs =  viewSpaceService.getCommentLogBySpaceId(initSpaceId);
        assertNotNull(commentLogs);
        assertEquals(1, commentLogs.size());

        //景区相应的评论项是否正确更新
        ViewSpace newViewSpace = viewSpaceService.getFullViewSpace(initSpaceId);
        assertEquals(1, newViewSpace.getWantNum());

        //抛出IpCommentedException异常
        viewSpaceService.addCommentLog(commentLog);
    }

}
