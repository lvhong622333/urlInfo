package com.lvhong.web.pojo;

import java.util.Date;

public class TmFileRoute {
	
	private Long id;

	private String routeName;

	private String fileName;

	private Long fileSize;

	private Date createTime;

	private Long createBy;

	private String isvalid;
	
	public TmFileRoute() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public static class Builder{
		
		private TmFileRoute tmFileRoute;
		
		public Builder() {
			tmFileRoute = new TmFileRoute();
		}
		
		public Builder id(Long id) {
			tmFileRoute.id = id;
			return this;
		}
		
		public Builder routeName(String routeName) {
			tmFileRoute.routeName = routeName;
			return this;
		}
		
		public Builder fileName(String fileName) {
			tmFileRoute.fileName = fileName;
			return this;
		}
		
		public Builder fileSize(Long fileSize) {
			tmFileRoute.fileSize = fileSize;
			return this;
		}
		
		public Builder createTime(Date createTime) {
			tmFileRoute.createTime = createTime;
			return this;
		}
		
		public Builder createBy(Long createBy) {
			tmFileRoute.createBy = createBy;
			return this;
		}
		
		public Builder isvalid(String isvalid) {
			tmFileRoute.isvalid = isvalid;
			return this;
		}
		
		public TmFileRoute build() {
			return tmFileRoute;
		}
		
	}

}