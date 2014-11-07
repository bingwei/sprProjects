package com.smart.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.smart.domain.ViewPoint;

import java.util.List;

@Repository
public class ViewPointHibernateDao extends BaseDao {

    public void addViewPoint(ViewPoint viewPoint) {
        getHibernateTemplate().save(viewPoint);
    }

    public ViewPoint getViewPointById(int pointId) {
        return getHibernateTemplate().get(ViewPoint.class, pointId);
    }

    public List<ViewPoint> findViewPointByName(String pointName) {
        return (List<ViewPoint>) getHibernateTemplate().find(
                "from ViewPoint v where v.pointName like ?", pointName + "%");
    }
}
