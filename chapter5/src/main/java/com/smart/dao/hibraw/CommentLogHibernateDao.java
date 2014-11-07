package com.smart.dao.hibraw;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.domain.CommentLog;

@Repository
public class CommentLogHibernateDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void addCommentLog(CommentLog commentLog) {
        sessionFactory.getCurrentSession().save(commentLog);
    }

    public CommentLog getCommentLog(int CommentLogId) {
        // TODO Auto-generated method stub
        return null;
    }

}
