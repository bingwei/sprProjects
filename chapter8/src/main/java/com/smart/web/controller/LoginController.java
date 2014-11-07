package com.smart.web.controller;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smart.cons.CommonConstant;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smart.domain.User;

/**
 * <br>
 * <b>类描述:</b>
 * <pre>
 *   景区登录控制器，处理登录验证、注销等操作
 * </pre>
 * @see
 * @since
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    /**
     * 自动注入
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = userService.findUserByUserName(userName);
        if (user == null) {
            request.setAttribute("errorMsg", "用户名不存在");
            return new ModelAndView("forward:/login.jsp");
        } else if (!user.getPassword().equals(password)) {
            request.setAttribute("errorMsg", "用户密码不正确");
            return new ModelAndView("forward:/login.jsp");
        } else {
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.saveLoginInfo(user);
            setSessionUser(request,user);
            return new ModelAndView("success");
        }
    }

    /**
     *登录注销
     * @param session
     * @return
     */
    @RequestMapping("/doLogout")
    public String logout(HttpSession session) {
        session.removeAttribute(CommonConstant.USER_CONTEXT);
        return "forward:/index.jsp";
    }
}
