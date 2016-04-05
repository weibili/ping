<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="<%=path%>/css/manage.css" media="screen" rel="stylesheet"
	type="text/css" />
<script src="<%=path%>/js/jquery-1.4.4.min.js" type="text/javascript"></script>
</head>
<body>
	<div class="manage_container">
		<div class="main">
			<h1>气象基站监控</h1>
			<div class="table_box">
				<form action="<%=path%>/login/login" method="post">
					<fieldset class="solid">
						<div align="center">
							<label>用户名：</label> <input type="text" name="id" />
						</div><br/>
						<div align="center">
							<label>密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> <input type="password" name="password" />
						</div>
						<div align="center"><label>${msg}</label></div>
						<br/>
						<div align="center">
							<input value="登陆" type="submit" />
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
