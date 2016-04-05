package com.demo.login;


import com.demo.common.model.User;
import com.demo.tool.Constant;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor {

	/* (non-Javadoc)
	 * @see com.jfinal.aop.Interceptor#intercept(com.jfinal.core.ActionInvocation)
	 */
	@Override
	public void intercept(Invocation ai) {
		Controller controller = ai.getController();
		User user = controller.getSessionAttr(Constant.SESSION_NAME);
		if (user != null) { // 已登录
			controller.setAttr("current_url", ai.getActionKey());
			ai.invoke();
		} else { // 未登录
			controller.redirect("/login");
		}
	}

}
