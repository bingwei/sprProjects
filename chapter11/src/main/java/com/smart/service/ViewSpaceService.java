package com.smart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.CommentLogDao;
import com.smart.dao.UserDao;
import com.smart.dao.ViewPointDao;
import com.smart.dao.ViewSpaceDao;
import com.smart.domain.CommentLog;
import com.smart.domain.User;
import com.smart.domain.ViewPoint;
import com.smart.domain.ViewSpace;
import com.smart.service.exception.IpCommentedException;

/**
 * 旅游网站的服务类
 */
@Service
public class ViewSpaceService {

	@Autowired
	protected ViewSpaceDao viewSpaceDao;
	@Autowired
	protected ViewPointDao viewPointDao;
	@Autowired
	protected CommentLogDao commentLogDao;

	// --------------------旅游景区维护------------------
	/**
	 * 新增一个旅游景区
	 * @param viewSpace
	 */
	public void addViewSpace(ViewSpace viewSpace) {
		viewSpaceDao.save(viewSpace);
	}

	/**
	 * 删除一个景区
	 * @param spaceId
	 */
	public void deleteViewSpace(int spaceId) {
		//删除景区相关的评论
		commentLogDao.removeBySpaceId(spaceId);
        //删除景区		
		ViewSpace vs = viewSpaceDao.load(spaceId);
		viewSpaceDao.remove(vs);
		
	}

	/**
	 * 更改景区信息
	 * @param viewSpace
	 */
	public void updateViewSpace(ViewSpace viewSpace) {
		viewSpaceDao.update(viewSpace);
	}

	/**
	 * 根据景区名模糊查询所有匹配的景区
	 * @param name 查询条件
	 * @return
	 */
	public List<ViewSpace> queryViewSpaceByName(String name) {
		return viewSpaceDao.queryByName(name);
	}
	
	/**
	 * 获取某个景区管理员所属的所有景区
	 * @param userId
	 * @return
	 */
	public List<ViewSpace> queryViewSpaceByUserId(int userId) {
		return viewSpaceDao.queryByUserId(userId);
	}
	
	/**
	 * 获取所有景区对象
	 * @return
	 */
	public List<ViewSpace> getAllViewSpaces(){
		return viewSpaceDao.loadAll();
	}
    
	/**
	 * 获取某个景区对象及其关联的对象
	 * @param spaceId
	 * @return
	 */
	public ViewSpace getFullViewSpace(int spaceId) {
		ViewSpace vs = viewSpaceDao.get(spaceId);
		if (vs != null) {// 对ViewSpace的关联对象执行延迟加载初始化
			viewSpaceDao.initialize(vs.getViewPoints());
			viewSpaceDao.initialize(vs.getUser());
		}
		return vs;
	}
	
	/**
	 * 获取某个景区对象，关联的对象信息未加载
	 * @param spaceId
	 * @return
	 */
	public ViewSpace getViewSpace(int spaceId) {
		return viewSpaceDao.get(spaceId);
	}

	/**
	 * 对景区进行评论，同时更新景区评论项的数值
	 * @param commentLog
	 */
	public void addCommentLog(CommentLog commentLog) throws IpCommentedException{		
		ViewSpace vs = commentLog.getViewSpace();
		vs = viewSpaceDao.load(vs.getSpaceId());
		boolean commented =commentLogDao.isIpCommented(vs.getSpaceId(),commentLog.getIp());
		if (commented) {
			throw new IpCommentedException("这个IP已经对景区进行了评论。");
		}
		commentLog.setViewSpace(vs);
		switch (commentLog.getCommentType()) {
		case CommentLog.WANT_TO_GO:
            vs.setWantNum(vs.getWantNum()+1);
			break;
		case CommentLog.HAVE_BEAN_TO:
            vs.setBeenNum(vs.getBeenNum()+1);
			break;
		case CommentLog.DONT_WANT_TO_GO:
            vs.setNotwantNum(vs.getNotwantNum()+1);
			break;
		default:
			throw new RuntimeException("评论类型不正确。");
		}
		commentLogDao.save(commentLog);
	}

    public List<CommentLog> getCommentLogBySpaceId(int spaceId){
           return commentLogDao.getCommentLogBySpaceId(spaceId);
    }

	// -------------------------------------------------

	// --------------------旅游景点维护------------------
	/**
	 * 添加一个景区
	 */
	public void addViewPoint(ViewPoint viewPoint) {
		viewPointDao.save(viewPoint);
	}
	
	/**
	 * 获取某个景点对象
	 * @param pointId
	 * @return
	 */
	public ViewPoint getFullViewPoint(int pointId) {
		ViewPoint vp = viewPointDao.get(pointId);
		//viewPointDao.initialize(vp.getViewSpace());
		return vp;
	}

	/**
	 * 删除某个景点对象
	 * @param pointId
	 */
	public void deleteViewPoint(int pointId) {
		ViewPoint vp = viewPointDao.load(pointId);
		viewPointDao.remove(vp);
	}

	/**
	 * 更改某个景点的信息
	 * @param viewPoint
	 */
	public void updateViewPoint(ViewPoint viewPoint) {
		viewPointDao.update(viewPoint);
	}

	// -------------------------------------------------



	public void setViewSpaceDao(ViewSpaceDao viewSpaceDao) {
		this.viewSpaceDao = viewSpaceDao;
	}

	public void setViewPointDao(ViewPointDao viewPointDao) {
		this.viewPointDao = viewPointDao;
	}

	public void setCommentLogDao(CommentLogDao commentLogDao) {
		this.commentLogDao = commentLogDao;
	}

}
