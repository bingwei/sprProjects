package com.smart.service;

import com.smart.domain.ViewSpace;

public interface IViewSpace {
    void addViewSpace(ViewSpace space);

    void updateViewSpace(ViewSpace space);

    ViewSpace getViewSpace(int spaceId);

    int getViewSpaceNum();

    void deleteViewSpace(int spaceId);

}
