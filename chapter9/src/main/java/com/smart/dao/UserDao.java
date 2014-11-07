package com.smart.dao;

import com.smart.domain.User;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * User对象Dao
 */
@Repository
public class UserDao {

    //用于临时保存用户信息，在下一个测试点中，将完善为保存到数据库中
    private Map<String,User> users = new HashMap<String,User>();
   
    public void save(User user){
        users.put(user.getUserName(),user);
    }

    public User getUserByUserName(String userName){
       return users.get(userName);
    }
    
    
    private final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";
    private final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";

    /**
     * 根据用户名查询User对象
     * @param userName 用户名
     * @return 对应userName的User对象，如果不存在，返回null。
     */
    public User getUserByUserName2(String userName){
        List<User> users = null;//(List<User>)find(GET_USER_BY_USERNAME,userName);
        if (users.size() == 0) {
            return null;
        }else{
            return users.get(0);
        }
    }

    /**
     * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
     * @param userName 用户名查询条件
     * @return 用户名前缀匹配的所有User对象
     */
    public List<User> queryUserByUserName(String userName){
        return null;
        //return (List<User>)find(QUERY_USER_BY_USERNAME,userName+"%");
    }
}
