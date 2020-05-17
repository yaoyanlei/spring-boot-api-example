package com.apelab.example.repository.domain;

import java.util.Date;

/**
 * entity基类 Created by yaoyanlei on 2020/5/16
 */
public class BaseDomain<T> {

	private T id;
	private Date createTime;
	private String createBy;
	private Date updateTime;
	private String updateBy;
	private int deleteFlag;

	public T getId() {
		return id;
	}

	public void setId(final T id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(final String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(final String updateBy) {
		this.updateBy = updateBy;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(final int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
