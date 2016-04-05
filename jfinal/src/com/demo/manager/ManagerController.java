package com.demo.manager;

import com.demo.common.model.Manager;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class ManagerController extends Controller {
	public void index() {
		setAttr("managerPage", Manager.dao.paginate(getParaToInt(0, 1), 10));
		render("manager.jsp");
	}
	public void add() {
		render("add.jsp");
	}
	@Before(ManagerValidator.class)
	public void save() {
		getModel(Manager.class).save();
		redirect("/manager");
	}
	
	public void edit() {
		setAttr("manager", Manager.dao.findById(getPara("id")));
	}
	@Before(ManagerValidator.class)
	public void update() {
		getModel(Manager.class).update();
		redirect("/manager");
	}
	
	public void delete() {
		Manager.dao.deleteById(getPara("id"));
		redirect("/manager");
	}
}


