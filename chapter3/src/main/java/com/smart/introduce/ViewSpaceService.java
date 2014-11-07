package com.smart.introduce;

/**
 * 景区管理的服务类
 */
public class ViewSpaceService {

    public void deleteViewSpace(int spaceId) {
        System.out.println("模拟删除景区记录:" + spaceId);
        try {
            Thread.currentThread().sleep(20);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteViewPoint(int pointId) {
        System.out.println("模拟删除景点记录:" + pointId);
        try {
            Thread.currentThread().sleep(40);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
