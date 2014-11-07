/**
 * @(#)sample.testng.TestNGGroupsTest
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
package sample.testng;
import com.smart.domain.User;
import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * @author 林开雄
 * @version ${unitils.version}
 *          功能说明：
 */
public class TestNGDependsTest {

    @Test
    public void testMethod1() {
    }

    @Test
    public void testMethod2() {
        assertNull(new User());
    }

    @Test(dependsOnMethods = {"testMethod1","testMethod2"},alwaysRun=true)
    public void testMethod3() {

    }

}
