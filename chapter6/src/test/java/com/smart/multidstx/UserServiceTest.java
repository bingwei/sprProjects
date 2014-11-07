/**
 * @(#)com.smart.multidstx.UserServiceTest
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
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import java.util.Date;

/**
 * @author 林开雄
 * @version ${unitils.version}
 *          功能说明：
 */
@SpringApplicationContext( {"com/smart/multidstx/applicationContext.xml"})
public class UserServiceTest  extends  UnitilsTestNG{
    
    @SpringBean("userService")
    private UserService userService;

    @Test
    public void testAddLoginLog(){
        LoginLog log1 = new LoginLog();
        log1.setUserId(11);
        log1.setLoginLogId(11);
        log1.setIp("192.168.1.1");
        log1.setLoginDate(new Date());
        userService.addLoginLog1(log1);

/*        LoginLog log2 = new LoginLog();
        log2.setUserId(21);
        log2.setLoginLogId(21);
        log2.setIp("192.168.1.1");
        log2.setLoginDate(new Date());
        userService.addLoginLog2(log2);*/
    }
}
