package com.smart.dao;


import com.smart.domain.*;

public interface ViewSpaceDao {
    void addViewSpace(ViewSpace viewSpace);

    void updateViewSpace(ViewSpace viewSpace);

    ViewSpace getViewSpace(int forumId);

    int getViewSpaceNum();

    void deleteViewSpace(int spaceId);
}
