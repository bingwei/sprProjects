package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;


    public User findUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    public User getUserById(int userId) {
        return userDao.get(userId);
    }



    public void saveLoginInfo(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.update(user);
        loginLogDao.save(loginLog);
    }


}
