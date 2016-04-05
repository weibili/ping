<#macro layout>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link href="<%=path%>/css/manage.css" media="screen" rel="stylesheet"
	type="text/css" />
<script src="<%=path%>/js/jquery-1.4.4.min.js" type="text/javascript"></script>
<body>
	<div class="manage_container">
		<div class="manage_head">
			<div class="manage_logo"><a href="http://www.jfinal.com">JFinal web framework</a></div>
			<div id="nav">
				<ul>
					<li><a href="/"><b>é¦é¡µ</b></a></li>
					<li><a href="/blog"><b>Blogç®¡ç</b></a></li>
				</ul>
			</div>
		</div>
		<div class="main">
			<#nested>
		</div>
	</div>
</body>
</html>
</#macro>