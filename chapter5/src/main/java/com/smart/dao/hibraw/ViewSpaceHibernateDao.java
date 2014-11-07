package com.smart.dao.hibraw;

import java.util.List;

import com.smart.domain.ViewSpace;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ViewSpaceHibernateDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addViewSpace(ViewSpace viewSpace) {
        sessionFactory.getCurrentSession().save(viewSpace);
    }

    public void updateViewSpace(ViewSpace ViewSpace) {
        sessionFactory.getCurrentSession().update(ViewSpace);
    }

    public ViewSpace getViewSpace(int ViewSpaceId) {
        return (ViewSpace) sessionFactory.getCurrentSession().load(ViewSpace.class,
                ViewSpaceId);
    }

    public long getViewSpaceNum() {
        Object obj = sessionFactory.getCurrentSession().createQuery(
                "select count(v.spaceId) from ViewSpace v").list().iterator();
        return (Long) obj;
    }

    public List<ViewSpace> findViewSpaceByName(String spaceName) {
        List list = sessionFactory
                .getCurrentSession()
                .createQuery("from ViewSpace v where v.spaceName like ?")
                .setString(0, spaceName + "%")
                .list();
        return (List<ViewSpace>) list;
    }
}
