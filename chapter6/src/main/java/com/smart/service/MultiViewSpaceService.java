package com.smart.service;

import com.smart.ViewPointTransactional;
import com.smart.ViewSpaceTransactional;
import com.smart.dao.ViewPointDao;
import com.smart.dao.ViewSpaceDao;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;

public class MultiViewSpaceService {
	private ViewSpaceDao viewSpaceDao;
	private ViewPointDao viewPointDao;

//	@Transactional("viewPoint")
	@ViewPointTransactional
	public void addViewPoint(ViewPoint viewPoint) throws Exception {
       System.out.println("viewPoint tx");
	}
	
//	@Transactional("viewSpace")
	@ViewSpaceTransactional
	public void updateViewSpace(ViewSpace viewSpace) {
		System.out.println("viewSpace tx");
	}
	
	public ViewSpaceDao getViewSpaceDao() {
		return viewSpaceDao;
	}
	public void setViewSpaceDao(ViewSpaceDao viewSpaceDao) {
		this.viewSpaceDao = viewSpaceDao;
	}
	public ViewPointDao getViewPointDao() {
		return viewPointDao;
	}
	public void setViewPointDao(ViewPointDao viewPointDao) {
		this.viewPointDao = viewPointDao;
	}


}
