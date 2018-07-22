package com.lvhong.core.listener;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

@SuppressWarnings("serial")
public class UrlTypeListen implements TaskListener {
	
	@Resource
	private RuntimeService runtimeService;

	@Override
	public void notify(DelegateTask delegateTask) {
		String user = (String) delegateTask.getVariable("user");
		delegateTask.setAssignee(user);
	}

}
