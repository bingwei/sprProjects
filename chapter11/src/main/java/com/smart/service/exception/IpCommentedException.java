package com.smart.service.exception;

/**
 * 已经对景点进行了评论的IP不允许再次评论，否则抛出此异常
 */
public class IpCommentedException extends Exception {

	public IpCommentedException(String msg) {
		super(msg);
	}
 
}
