package com.smart.web;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:d:/actionSpring/chapter/chapter1/src/main/webapp/WEB-INF/viewspace-servlet.xml"})
public class LoginControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AnnotationMethodHandlerAdapter handlerAdapter;
    @Autowired
    private LoginController controller;
	//声明Request与Response模拟对象
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	//执行测试前先初始模拟对象
	@BeforeMethod
	public void before() {
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
        request.setAttribute(HandlerMapping.INTROSPECT_TYPE_LEVEL_MAPPING, true); //Spring3.1 存在的BUG

	}

	// 测试LoginController#loginCheck()方法
	@Test
	public void loginCheck() throws Exception {
        //测试登陆成功的情况
	    request.setRequestURI("/admin/loginCheck.html");
		request.addParameter("userName", "admin"); // 设置请求URL及参数
		request.addParameter("password", "123456");

		//向控制发起请求 ” /loginCheck.html”
		ModelAndView mav = handlerAdapter.handle(request, response, controller);
		User user = (User) request.getSession().getAttribute("user");
		assertNotNull(mav);
		assertEquals(mav.getViewName(), "main");
		assertNotNull(user);
        request.getSession().removeAttribute("user");

        //测试登陆失败的情况
        request.setRequestURI("/admin/loginCheck.html");
        request.addParameter("userName", "test");
        request.addParameter("password", "123456");

        mav = handlerAdapter.handle(request, response, controller);
        user = (User) request.getSession().getAttribute("user");
        assertNotNull(mav);
        assertEquals(mav.getViewName(), "login");
        assertNull(user);

	}
	


}
