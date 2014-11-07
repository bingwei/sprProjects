/**
 * @(#)com.smart.multidstx.Main
 *
 * 版权声明 亿力科技 版权所有,违者必究

 * <br> Copyright：Copyright (c)  2012 
 * <br> Company: 亿力科技 
 * <br> Author: 林开雄
 * <br>  Date：12-3-4
 * <br> Version： ${unitils.version}
 *————————————————————————————————————
 * 修改记录
 *    修改者：
 *    修改时间：
 *    修改原因：
 *—————————————————————————————————————
 */
package com.smart.multidstx;

import com.smart.LoginLog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author 林开雄
 * @version ${unitils.version}
 *          功能说明：
 */
public class Main {
    public static void main(String[] args) {
       // BasicConfigurator.configure();//默认配置
       // PropertyConfigurator.configure("c:/log4j.properties");

        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/multidstx/applicationContext.xml");
        UserService service = (UserService) ctx.getBean("userService");
        LoginLog log1 = new LoginLog();
        log1.setUserId(111);
        log1.setLoginLogId(116);
        log1.setIp("192.168.1.1");
        log1.setLoginDate(new Date());
        service.addLoginLog(log1);
       /* LoginLog log2 = new LoginLog();
        log2.setUserId(111);
        log2.setLoginLogId(111);
        log2.setIp("192.168.1.1");
        log2.setLoginDate(new Date());
        service.addLoginLog2(log2);*/
    }

}
