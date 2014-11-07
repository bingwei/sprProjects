package com.smart.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.smart.domain.ViewSpace;

@Repository
public class ViewSpaceHibernateDao extends BaseDao {

    public void addViewSpace(ViewSpace ViewSpace) {
        getHibernateTemplate().save(ViewSpace);
    }

    public void updateViewSpace(ViewSpace ViewSpace) {
        getHibernateTemplate().update(ViewSpace);
    }

    public ViewSpace getViewSpace(int ViewSpaceId) {
        return getHibernateTemplate().get(ViewSpace.class, ViewSpaceId);
    }

    public long getViewSpaceNum() {
        Object obj = getHibernateTemplate().iterate(
                "select count(v.spaceId) from ViewSpace v").next();
        return (Long) obj;
    }

    public long getViewSpaceNum2() {
        Long ViewSpaceNum = getHibernateTemplate().execute(
                new HibernateCallback<Long>() {
                    public Long doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Object obj = session.createQuery("select count(v.spaceId) from ViewSpace v")
                                .list()
                                .iterator()
                                .next();
                        return (Long) obj;
                    }
                });
        return ViewSpaceNum;
    }

    public List<ViewSpace> findViewSpaceByName(String spaceName) {
        return (List<ViewSpace>) getHibernateTemplate().find(
                "from ViewSpace v where v.spaceName like ?", spaceName + "%");
    }
}
