package com.pronghorn.core.generic;

/** 分页实体
 * 
 * @author 王广开
 * @version 2017年3月6日 16:29:20 创建
 * @version 2017年3月25日 21:22:37 增加查询实体
 **/
public class PageVo<T> {

	private int offset;
	private int limit;
	private String orderBy;
	private T model;
	private String curemp;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getCuremp() {
		return curemp;
	}

	public void setCuremp(String curemp) {
		this.curemp = curemp;
	}
}