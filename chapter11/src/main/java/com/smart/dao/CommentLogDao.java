package com.smart.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.domain.CommentLog;
/**
 * User对象Dao
 */
@Repository
public class CommentLogDao extends BaseDao<CommentLog> {
	
	/**
	 * 判断某一个IP是否已经对id为spaceId的景区进行了评论
	 * @param spaceId 景区ID
	 * @param ip 
	 * @return true 已经进行了评论 false 尚未
	 */
	public boolean isIpCommented(int spaceId,String ip){
		String hql = "from CommentLog c where c.viewSpace.spaceId = ? and c.ip =?";
		List result = find(hql,spaceId,ip);
		return result.size() > 0 ?true:false;
	}
	
	/**
	 * 删除景区的所有评论日志
	 * @param spaceId
	 */
	public void removeBySpaceId(int spaceId){
		String hql = "from CommentLog c where c.viewSpace.spaceId = ? ";
		List<CommentLog> commentLogs = (List<CommentLog>)find(hql,spaceId);
		for (CommentLog commentLog : commentLogs) {
			remove(commentLog);
		}
	}

    public List<CommentLog> getCommentLogBySpaceId(int spaceId){
        String hql = "from CommentLog c where c.viewSpace.spaceId = ? ";
        List<CommentLog> commentLogs = (List<CommentLog>)find(hql,spaceId);
        return commentLogs;
    }
	
}
