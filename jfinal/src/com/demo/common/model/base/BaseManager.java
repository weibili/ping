package com.demo.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseManager<M extends BaseManager<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setTel(java.lang.String tel) {
		set("tel", tel);
	}

	public java.lang.String getTel() {
		return get("tel");
	}

	public void setUseFlag(java.lang.String useFlag) {
		set("useFlag", useFlag);
	}

	public java.lang.String getUseFlag() {
		return get("useFlag");
	}

	public void setSendType(java.lang.String sendType) {
		set("sendType", sendType);
	}

	public java.lang.String getSendType() {
		return get("sendType");
	}

}
