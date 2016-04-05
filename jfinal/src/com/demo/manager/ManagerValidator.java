package com.demo.manager;

import com.demo.common.model.Host;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class ManagerValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("manager.name", "nameMsg", "请输入姓名");
		validateRequiredString("manager.tel", "telMsg", "请输入电话");
		validateRequiredString("manager.useFlag", "useMsg", "请选择是否");
		validateRequiredString("manager.sendType", "sendTypeMsg", "请选择是否");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Host.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/manager/save"))
			controller.render("add.html");
		else if (actionKey.equals("/manager/update"))
			controller.render("edit.html");
	}
}
