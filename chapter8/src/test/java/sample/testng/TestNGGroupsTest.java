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

import org.testng.annotations.*;
import static org.testng.Assert.*;

/**
 * @author 林开雄
 * @version ${unitils.version}
 *          功能说明：
 */
@Test(groups = {"class-group"})
public class TestNGGroupsTest {

    @Test(groups = {"group1", "group2"})
    public void testMethod1() {
    }

    @Test(groups = {"group1", "group2"})
    public void testMethod2() {
    }

    @Test(groups = {"group1"})
    public void testMethod3() {
    }

}
