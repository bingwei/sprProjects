package com.smart.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ViewSpaceService {
    private UserService userService;

    @Transactional(propagation=Propagation.NEVER)
    public void addTopic(){
    	//add Topic
		userService.addCredits();
    }
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
