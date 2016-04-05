package com.demo.host;

import com.demo.common.model.Host;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class HostValidator extends Validator {
	
	protected void validate(Controller controller) {
		validateRequiredString("host.hostIp", "ipMsg", "请输入IP!");
		validateRequiredString("host.pingFlag", "pingFlag", "请选择是否!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepModel(Host.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/host/save"))
			controller.render("add.html");
		else if (actionKey.equals("/host/update"))
			controller.render("edit.html");
	}
}
