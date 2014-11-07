/**
 * @(#)com.smart.web.UserServiceTest
 *
 * 版权声明 亿力吉奥 版权所有,违者必究

 * <br> Copyright：Copyright (c)  2012 
 * <br> Company: 亿力吉奥 
 * <br> Author: 林开雄
 * <br>  Date：12-4-15
 * <br> Version： 0.1
 *————————————————————————————————————
 * 修改记录
 *    修改者：
 *    修改时间：
 *    修改原因：
 *—————————————————————————————————————
 */
package com.smart.web;

import com.smart.dao.UserDao;
import com.smart.domain.User;
import com.smart.service.BaseServiceTest;
import com.smart.service.UserService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;
/**
 * @author 林开雄
 * @version 0.1
 *          功能说明：
 */
public class UserServiceTest extends BaseServiceTest{

    @SpringBean("userService")
    private UserService userService;

    //测试用户名1
    private final String testUserName1 = "test1";
    private final String testUserName2 = "test2";

    @Mock UserDao userDao;

    //执行测试之前，先保存一个用户
    @BeforeMethod
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setUserName(testUserName2);
        user.setPassword("123456");
        //ReflectionTestUtils.setField(userService,"userDao", userDao);
        userService.setUserDao(userDao);
        when(userDao.getUserByUserName(testUserName1)).thenReturn(null);
        when(userDao.getUserByUserName(testUserName2)).thenReturn(user);
    }

    @Test
    public void testIsRepeatedUser(){
       //测试用户1不已存在
       boolean result1 =  userService.isRepeatedUser(testUserName1);
       assertFalse(result1);

        //测试用户2已存在
        boolean result2 =  userService.isRepeatedUser(testUserName2);
        assertTrue(result2);
    }
}
