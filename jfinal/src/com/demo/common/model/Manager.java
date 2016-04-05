package com.demo.common.model;

import com.demo.common.model.base.BaseManager;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Manager extends BaseManager<Manager> {
	public static final Manager dao = new Manager();
	public Page<Manager> paginate(int pageNumber, int pageSize) {
	
		return paginate(pageNumber, pageSize, "select *", "from manager order by id asc");
	}
}