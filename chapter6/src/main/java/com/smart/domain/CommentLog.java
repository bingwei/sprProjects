package com.smart.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;


public class CommentLog{
	
	/**
	 * 想去
	 */
	public static final int WANT_TO_GO = 1;
	
	/**
	 * 已经去过了
	 */
	public static final int HAVE_BEAN_TO = 2;
	
	/**
	 * 不想去
	 */
	public static final int DONT_WANT_TO_GO = 3;
	
	

	protected int logId;

	
	protected int  spaceId;

	protected String ip;


	protected int commentType;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}



	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCommentType() {
		return commentType;
	}

	public void setCommentType(int commentType) {
		this.commentType = commentType;
	}

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }
}