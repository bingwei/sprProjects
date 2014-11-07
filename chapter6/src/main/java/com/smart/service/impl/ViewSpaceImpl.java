package com.smart.service.impl;

import com.smart.dao.ViewPointDao;
import com.smart.dao.ViewSpaceDao;
import com.smart.domain.ViewSpace;
import com.smart.service.IViewSpace;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ViewSpaceImpl implements IViewSpace {
	private ViewSpaceDao viewSpaceDao;
	private ViewPointDao viewPointDao;


	@Transactional(readOnly=true)
	public ViewSpace getViewSpace(int spaceId) {
		return viewSpaceDao.getViewSpace( spaceId);
	}
	public void updateViewSpace(ViewSpace viewSpace) {
        viewSpaceDao.updateViewSpace(viewSpace);
	}
	public int getViewSpaceNum() {
		return viewSpaceDao.getViewSpaceNum();
	}
	
	public void setViewSpaceDao(ViewSpaceDao viewSpaceDao) {
		this.viewSpaceDao = viewSpaceDao;
	}

	public void setViewPointDao(ViewPointDao viewPointDao) {
		this.viewPointDao = viewPointDao;
	}

    public void addViewSpace(ViewSpace space) {
        this.viewSpaceDao.addViewSpace(space);
    }

    public void deleteViewSpace(int spaceId) {
        this.viewSpaceDao.deleteViewSpace(spaceId);
    }
}
