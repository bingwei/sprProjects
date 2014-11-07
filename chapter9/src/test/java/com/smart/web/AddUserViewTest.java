/**
 * @(#)com.smart.web.AddUserViewTest
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

import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringBeanByType;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * @author 林开雄
 * @version 0.1
 *          功能说明：
 */
public class AddUserViewTest extends BaseWebTest {

    @SpringBeanByType
    private UserManageController controller;


    @Test
    public void addUserView() throws Exception {
        //设置请求URI及方法
        request.setRequestURI("/admin/user/add");
        request.setMethod("GET");
        //验证结果
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/addUser");
    }
}
