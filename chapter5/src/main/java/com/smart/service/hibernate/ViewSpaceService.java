package com.smart.service.hibernate;

import java.util.List;

import com.smart.domain.CommentLog;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.dao.hibernate.ViewSpaceHibernateDao;
import com.smart.dao.hibernate.CommentLogHibernateDao;
import com.smart.dao.hibernate.ViewPointHibernateDao;


@Transactional
@Service
public class ViewSpaceService {

    @Autowired
    private ViewSpaceHibernateDao viewSpaceDao;

    @Autowired
    private ViewPointHibernateDao viewPointDao;

    @Autowired
    private CommentLogHibernateDao commentLogDao;

    public void addViewSpace(ViewSpace viewSpace) {
        viewSpaceDao.addViewSpace(viewSpace);
    }

    public void addViewPoint(ViewPoint viewPoint) {
        viewPointDao.addViewPoint(viewPoint);
    }

    public void addCommentLog(CommentLog commentLog) {
        commentLogDao.addCommentLog(commentLog);
    }

    public ViewSpace getViewSpace(int spaceId) {
        return viewSpaceDao.getViewSpace(spaceId);
    }

    public void updateViewSpace(ViewSpace viewSpace) {
        viewSpaceDao.updateViewSpace(viewSpace);
    }

    public long getViewSpaceNum() {
        return viewSpaceDao.getViewSpaceNum();
    }

    public List findViewSpaceByName(String spaceName) {
        return viewSpaceDao.findViewSpaceByName(spaceName);
    }

    public CommentLog getCommentLog(int logId) {
        return commentLogDao.getCommentLog(logId);
    }

}
