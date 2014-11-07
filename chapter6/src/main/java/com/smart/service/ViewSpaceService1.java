package com.smart.service;

import com.smart.dao.ViewSpaceDao;
import com.smart.domain.ViewSpace;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class ViewSpaceService1 {
	private ViewSpaceDao viewSpaceDao;
	private TransactionTemplate template;
	public void addViewSpace(final ViewSpace viewSpace) {
		
		template.execute(new TransactionCallbackWithoutResult() {
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				viewSpaceDao.addViewSpace(viewSpace);
			}
		});

	}
	public void setViewSpaceDao(ViewSpaceDao viewSpaceDao) {
		this.viewSpaceDao = viewSpaceDao;
	}
	public void setTemplate(TransactionTemplate template) {
		this.template = template;
	}

	
	
}
