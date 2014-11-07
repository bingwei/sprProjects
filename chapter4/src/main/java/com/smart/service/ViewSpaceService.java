package com.smart.service;

import com.smart.dao.ViewSpaceDao;
import com.smart.domain.ViewSpace;

public class ViewSpaceService implements IViewSpaceService {
    private ViewSpaceDao viewSpaceDao;

    public void setForumDao(ViewSpaceDao viewSpaceDao) {
        this.viewSpaceDao = viewSpaceDao;
    }

    public void addViewSpace(ViewSpace viewSpace) {
        viewSpaceDao.addViewSpace(viewSpace);
    }
}
