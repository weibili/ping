<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp" %>
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
</head>
<body>
	<div class="manage_container">
		<%@ include file="/common/header.html" %>
		<div class="main">
			<h1>人员管理 ---&gt; 添加人员</h1>
			<div class="form_box">
				<form action="<%=path%>/manager/save" method="post">
					<%@ include file="/manager/_form.jsp"%>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

