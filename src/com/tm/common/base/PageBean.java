package com.tm.common.base;

import java.util.List;

/**
 * 分页
 */
public class PageBean {
	private int count;
	private List<?> list;
	public void pageBean(){
		this.count=0;
		this.list=null;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
}
