package com.smart.advice;

import com.smart.domain.ViewSpace;

import java.sql.SQLException;

/**
 * 景区管理的服务类
 */
public class ViewSpaceService {

    public boolean deleteViewSpace(int spaceId) {

        throw new RuntimeException("运行异常。");
    }

    public void updateViewSpace(ViewSpace viewSpace) throws Exception {
        // do sth...
        throw new SQLException("数据更新操作异常。");

    }
}
