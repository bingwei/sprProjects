package com.smart.concept;


import com.smart.dao.ViewPointDao;
import com.smart.dao.ViewSpaceDao;
import com.smart.domain.ViewSpace;

/**
 * 景区管理的服务类
 */
public class ViewSpaceService {

    private TransactionManager transManager;
    private PerformanceMonitor pmonitor;
    private ViewSpaceDao viewSpaceDao;
    private ViewPointDao viewPointDao;

    /**
     * 新增一个旅游景区
     *
     * @param viewSpace
     */
    public void addViewSpace(ViewSpace viewSpace) {
        pmonitor.start();
        transManager.beginTransaction();
        viewSpaceDao.addViewSpace(viewSpace);
        transManager.endTransaction();
        pmonitor.end();
    }


    /**
     * 删除某个景点
     *
     * @param pointId
     */
    public void deleteViewPoint(int pointId) {
        pmonitor.start();
        transManager.beginTransaction();
        viewPointDao.deleteViewPoint(pointId);
        transManager.endTransaction();
        pmonitor.end();
    }


}
