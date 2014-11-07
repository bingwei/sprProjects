package com.smart.service;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.springframework.test.util.ReflectionTestUtils;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.User;

import static org.mockito.Mockito.*;

@SpringApplicationContext({"smart-service.xml", "smart-dao.xml"})
public class UserServiceTest extends UnitilsTestNG {
    @SpringBean("userService")
    private UserService userService;

    private UserDao userDao;

    private LoginLogDao loginLogDao;

    @BeforeMethod
    public void init() {
        userDao = mock(UserDao.class);
        loginLogDao = mock(LoginLogDao.class);

    }


    //@Test
    public void findUserByUserName() {
        User user = new User();
        user.setUserName("tom");
        user.setPassword("1234");
        doReturn(user).when(userDao).getUserByUserName("tom");
        UserService userService = new UserService();
        ReflectionTestUtils.setField(userService, "userDao", userDao);


        User u = userService.findUserByUserName("tom");
        assertNotNull(u);
        assertThat(u.getUserName(), equalTo(user.getUserName()));
        verify(userDao, times(1)).getUserByUserName("tom");
    }


    @Test
    //@DataSet("Users.xls")
    public void loginSuccess() {
       /* User user = userService.findUserByUserName("tom");
        Date now = new Date();
        user.setLastVisit(now);
        userService.saveLoginInfo(user);
        User u = userService.findUserByUserName("tom");
        assertEquals(now, u.getLastVisit());*/
    }

}
