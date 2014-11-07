package com.smart.dao.hibraw;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.domain.ViewPoint;

@Repository
public class ViewPointHibernateDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addViewPoint(ViewPoint viewPoint) {
        sessionFactory.getCurrentSession().save(viewPoint);
    }
}
