package com.demo.host;

import com.demo.common.model.Host;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class HostController extends Controller {
	public void index() {
		String hostIp = getPara("hostIp");
		String hostName =getPara("hostName");
		setAttr("hostPage", Host.dao.paginate(getParaToInt(0, 1), 10,hostIp,hostName));
		render("host.jsp");
	}
	public void query() {
		String hostIp = getPara("hostIp");
		String hostName =getPara("hostName");
		setAttr("hostIp",  hostIp==null?"":hostIp);
		setAttr("hostName",  hostName==null?"":hostName);
		setAttr("hostPage", Host.dao.paginate(getParaToInt(0, 1), 10,hostIp,hostName));
		render("host.jsp");
	}
	public void add() {
		render("add.jsp");
	}
	@Before(HostValidator.class)
	public void save() {
		getModel(Host.class).save();
		redirect("/host");
	}
	
	public void edit() {
		setAttr("host", Host.dao.findById(getPara("ip")));
	}
	@Before(HostValidator.class)
	public void update() {
		getModel(Host.class).update();
		redirect("/host");
	}
	
	public void delete() {
		Host.dao.deleteById(getPara("ip"));
		redirect("/host");
	}
}


