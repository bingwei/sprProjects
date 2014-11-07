package com.smart.dao;

import java.util.List;

import com.smart.test.dataset.util.XlsDataSetBeanFactory;
import org.testng.Assert;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;
import org.testng.annotations.*;
import com.smart.domain.User;

@SpringApplicationContext({"smart-dao.xml"})
public class UserDaoTest extends UnitilsTestNG {

    @SpringBean("userDao")
    private UserDao userDao;

    @Test
    //@DataSet("UserDao.Users.xls")//准备数据
    public void findUserByUserName() {
     /*   User user = userDao.getUserByUserName("tony");
        Assert.assertNull(user, "不存在用户名为tony的用户!");
        user = userDao.getUserByUserName("admin");
        Assert.assertNotNull(user, "admin用户存在！");
        Assert.assertEquals("admin", user.getUserName());
        Assert.assertEquals("123456", user.getPassword());*/
    }

    // 验证数据库保存的正确性
    //@Test
    //@ExpectedDataSet("UserDao.ExpectedSaveUser.xls")// 准备验证数据
    public void saveUser() throws Exception {
        //通过XlsDataSetBeanFactory数据集绑定工厂创建测试实体
        User u = XlsDataSetBeanFactory.createBean(UserDaoTest.class, "UserDao.SaveUser.xls", "t_user", User.class);
        userDao.save(u);  //执行用户信息更新操作
    }

    //验证数据库保存的正确性
    @Test
    //@ExpectedDataSet("UserDao.ExpectedSaveUsers.xls")// 准备验证数据
    public void saveUsers() throws Exception {
     /*   List<User> users = XlsDataSetBeanFactory.createBeans(UserDaoTest.class, "UserDao.SaveUsers.xls", "t_user", User.class);
        for (User u : users) {
            userDao.save(u);
        }*/
    }

}
