package com.smart.dao;

import com.smart.domain.ViewSpace;

public class ViewSpaceDao extends BaseDao<ViewSpace> {
    public long getViewSpaceNum() {
        Object obj = getHibernateTemplate().iterate(
                "select count(v.spaceId) from ViewSpace v").next();
        return (Long) obj;
    }
}
