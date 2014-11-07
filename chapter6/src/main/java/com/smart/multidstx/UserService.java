package com.smart.multidstx;

import com.smart.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 陈雄华
 * @version 1.0
 */
@Service("userService")
public class UserService extends BaseService {

    @Autowired
    @Qualifier("jdbcTemplate1")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate jdbcTemplate2;

    public void addLoginLog1(LoginLog loginLog) {
        String sql = "insert into t_login_log(login_log_id,user_id,ip,login_datetime)VALUES(?,?,?,?)";
        jdbcTemplate1.update(sql, loginLog.getLoginLogId(), loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate());
    }

    public void addLoginLog2(LoginLog loginLog) {
        String sql = "insert into t_login_log(login_log_id,user_id,ip,login_datetime)VALUES(?,?,?,?)";
        jdbcTemplate2.update(sql, loginLog.getLoginLogId(), loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate());
    }


    public void addLoginLog(LoginLog loginLog){
       addLoginLog1(loginLog);
       loginLog.setLoginLogId(123);
       addLoginLog2(loginLog);
    }

}
