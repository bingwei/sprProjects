package com.smart.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.smart.domain.CommentLog;

@Repository
public class CommentLogHibernateDao extends BaseDao {

    public void addCommentLog(CommentLog commentLog) {
        getHibernateTemplate().save(commentLog);
    }

    public CommentLog getCommentLog(int CommentLogId) {
        // TODO Auto-generated method stub
        return null;
    }

}
