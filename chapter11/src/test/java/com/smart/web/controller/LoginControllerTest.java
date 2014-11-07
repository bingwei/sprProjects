/**
 * @(#)com.smart.web.controller.LoginControllerTest
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

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

import org.springframework.test.web.server.MockMvc;
import org.testng.annotations.*;

import static org.springframework.test.web.server.setup.MockMvcBuilders.xmlConfigSetup;

/**
 * @author 林开雄
 * @version ${unitils.version}
 *          功能说明：
 */
public class LoginControllerTest {
    private MockMvc mockMvc;
    String appContextPath = "classpath:/context/viewspace.xml";
    String webContextPath = "file:d:/actionSpring/chapter/chapter11/src/main/webapp/WEB-INF/viewspace-servlet.xml";
    String warDir = "src/main/webapp";

    @BeforeClass
    public void setUp() {
        mockMvc = xmlConfigSetup(appContextPath, webContextPath)
                .configureWebAppRootDir(warDir, false)
                .build();
    }


    @Test
    public void testDoLogin() throws Exception {
        mockMvc.perform(post("/login/doLogin").param("userName", "tom").param("password", "12345"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/login.jsp"));

        mockMvc.perform(post("/login/doLogin").param("userName", "admin").param("password", "12345"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/login.jsp"));

        mockMvc.perform(post("/login/doLogin").param("userName", "admin").param("password", "123456"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/jsp/success.jsp"));

    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/login/doLogout"))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/index.jsp"));
    }
}
