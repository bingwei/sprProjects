/**
 * @(#)com.smart.web.controller.ViewManageControllerTest
 *
 * 版权声明 亿力科技 版权所有,违者必究

 * <br> Copyright：Copyright (c)  2012 
 * <br> Company: 亿力科技 
 * <br> Author: 林开雄
 * <br>  Date：12-3-3
 * <br> Version： ${unitils.version}
 *————————————————————————————————————
 * 修改记录
 *    修改者：
 *    修改时间：
 *    修改原因：
 *—————————————————————————————————————
 */
package com.smart.web.controller;

import com.smart.domain.ViewSpace;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

/**
 * @author 林开雄
 * @version 3.1
 *          功能说明：
 */
public class ViewManageControllerTest extends BaseWebTest {
    //① 注入景区管理控制器
    @SpringBeanByType
    private ViewManageController controller;

    //② 测试景区首页处理控制器
    @Test
    public void listViewSpaces() throws Exception {
        //②-1设置请求URI及方法
        request.setRequestURI("/index");
        request.setMethod("GET");

         //②-2调用控制器
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        List<ViewSpace> viewSpaces = (List<ViewSpace>) request.getAttribute("viewSpaces");

       //②-3 验证结果
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/listViewSpaces");

        assertNotNull(viewSpaces);
        assertThat(viewSpaces.size(), greaterThan(1));
    }

    //③ 测试跳转添加景区页面
    @Test
    public void addViewSpacePage()throws Exception {
        //③-1设置请求URI及方法
        request.setRequestURI("/vs/add");

        //请求方法要与控制器中@RequestMapping设置方法一致
        request.setMethod("GET");

        //③-2验证结果
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/addViewSpace");
    }

   //添加添加景区
    @Test
    public void addViewSpace()throws Exception {
        request.setRequestURI("/vs/save");
        request.addParameter("spaceName", "测试景区");
        request.addParameter("description", "测试景区~~");
        request.setMethod("PUT");
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "redirect:/vs/index");
    }

    //测试打开更改景区页面
    @Test
    public void updateViewSpacePage()throws Exception {
        request.setRequestURI("/vs/1/edit");
        request.setMethod("GET");
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "/updateViewSpace");
    }

    //测试更改景区
    @Test
    public void updateViewSpace()throws Exception {
        request.setRequestURI("/vs/1/update");
        request.addParameter("spaceId", "1");
        request.addParameter("spaceName", "测试景区2");
        request.addParameter("description", "测试景区2~~");
        request.setMethod("PUT");
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "redirect:/vs/index");
    }
    //测试删除景区
    @Test
    public void deleteViewSpace()throws Exception {
        request.setRequestURI("/vs/1/delete");
        request.setMethod("DELETE");
        ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "redirect:/vs/index");
    }
}
