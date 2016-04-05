<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
</head>
<body>
	<div class="manage_container">
		<%@ include file="/common/header.html" %>
		<div class="main">
			<h1>基站管理 ---&gt; 修改基站</h1>
			<div class="form_box">
				<form action="<%=path%>/host/update" method="post">
					<%@ include file="/host/_form.jsp"%>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

