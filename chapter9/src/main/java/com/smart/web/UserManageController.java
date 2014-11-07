/**
 * @(#)com.smart.web.UserManagerController
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

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/user")
public class UserManageController {

    @RequestMapping("/add")
    public String  addUser() {
        return "/addUser";
    }

    @RequestMapping("/save")
    public String saveUser(HttpServletRequest request) {
          String userName = request.getParameter("userName");
          String password = request.getParameter("password");
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            request.setAttribute("errorMsg", "用户或密码不能为空！");
            return "/addUser";
        }else{
            return "/success";
        }
    }
}
