package com.smart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.domain.ViewSpace;

/**
 * User对象Dao
 */
@Repository
public class ViewSpaceDao extends BaseDao<ViewSpace> {

	/**
	 * 根据风景区名进行模糊查询的方法
	 * 
	 * @param name
	 * @return 风景区名称包括<code>name</code>的所有记录
	 */
	public List<ViewSpace> queryByName(String name) {
		String hql = "from ViewSpace vs where vs.spaceName like ?";
		name = "%" + name + "%";
		return (List<ViewSpace>) find(hql, name);
	}
    
	/**
	 * 获取用户管理的所有景区
	 * @param userId 景区管理员的Id
	 * @return 
	 */
	public List<ViewSpace> queryByUserId(int userId) {
		String hql = "from ViewSpace vs where vs.user.userId = ?";
		return (List<ViewSpace>) find(hql, userId);
	}
}
