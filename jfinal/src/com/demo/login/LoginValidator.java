package com.demo.login;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class LoginValidator extends Validator {

	
	protected void validate(Controller controller) {
		validateRequiredString("password", "msg", "密码不能为空!");
		validateRequiredString("id", "msg", "用户名不能为空!");
	}
	
	protected void handleError(Controller controller) {
		controller.keepPara();
	}
}
