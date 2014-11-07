package com.smart.service;

import com.smart.dao.UserDao;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UserDao userDao;

    public boolean isRepeatedUser(String userName) {
        User user  = userDao.getUserByUserName(userName);
        if(user==null){
            return false;
        }
        return true;
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
