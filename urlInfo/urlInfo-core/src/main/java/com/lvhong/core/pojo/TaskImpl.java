package com.lvhong.core.pojo;

import java.util.Date;

public class TaskImpl {

	public TaskImpl() {
		super();
	}

	public TaskImpl(String name, String description, String taskId, Date createTime,String processInstanceId) {
		super();
		this.name = name;
		this.description = description;
		this.taskId = taskId;
		this.createTime = createTime;
		this.processInstanceId = processInstanceId;
	}

	private String name;
	private String description;
	private String taskId;
	private String processInstanceId;
	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	
}
