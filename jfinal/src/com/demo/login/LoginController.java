package com.demo.login;

import java.util.Date;

import com.demo.common.model.User;
import com.demo.tool.Constant;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;

public class LoginController extends Controller {

	/**
	 * 登录页面
	 */
	@Clear
	public void index() {
		User user = this.getSessionAttr(Constant.SESSION_NAME);
		if (user != null) {
			redirect("/");
			return;
		}
		render("login.jsp");
	}

	/**
	 * 登录
	 */
	@Clear
	@Before(LoginValidator.class)
	public void login() {
		String id = getPara("id");
		String password = getPara("password");
		User user = User.dao.findById(id);
		if (user!=null&&user.getPassword().equals(password)) {
			setSessionAttr(Constant.SESSION_NAME, user);
			user.setLastlogints(new Date());
			User.dao.update();
			redirect("/");
		} else {
			this.setAttr("msg", "用户名密码错误");
			render("login.jsp");
		}
	}

	/**
	 * 登出
	 */
	public void logout() {
		this.removeSessionAttr(Constant.SESSION_NAME);
		redirect("/login");
	}
}
