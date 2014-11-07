package com.smart.domain;

import java.io.Serializable;

public class CommentLog implements Serializable {

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


    protected ViewSpace viewSpace;

    protected String ip;


    protected int commentType;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public ViewSpace getViewSpace() {
        return viewSpace;
    }

    public void setViewSpace(ViewSpace viewSpace) {
        this.viewSpace = viewSpace;
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

}