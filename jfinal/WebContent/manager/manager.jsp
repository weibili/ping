<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp"%>
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>

</head>
<body>
	<div class="manage_container">
		<%@ include file="/common/header.html"%>
		<div class="main">
			<h1>
				人员管理&nbsp;&nbsp; <a href="<%=path%>/manager/add">添加人员</a>
			</h1>
			<div class="table_box">
				<table class="list">
					<tbody>
						<tr>
							<th width="3%">人员编号</th>
							<th width="10%">人员姓名</th>
							<th width="4%">联系电话</th>
							<th width="4%">是否通知</th>
							<th width="4%">通知方式</th>
							<th width="3%">操作</th>
						</tr>
						<c:forEach items="${managerPage.list}" var="manager">
							<tr>
								<td style="text-align: left;">${manager.id}</td>
								<td style="text-align: left;">${manager.name}</td>
								<td style="text-align: left;">${manager.tel}</td>
								<td style="text-align: left;">${(manager.useFlag)=='0'?'否':'是'}</td>
								<td style="text-align: left;">${(manager.sendType)=='0'?'短信':'电话'}</td>
								<td style="text-align: center;">&nbsp;&nbsp;<a
									href="<%=path%>/manager/delete?id=${manager.id}">删除</a>
									&nbsp;&nbsp;<a href="<%=path%>/manager/edit?id=${manager.id}">修改</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:set var="currentPage" value="${managerPage.pageNumber}" />
				<c:set var="totalPage" value="${managerPage.totalPage}" />
				<c:set var="cp" value="${pageContext.request.contextPath }" />
				<c:set var="actionUrl" value="${cp}/manager/" />
				<c:set var="urlParas" value="" />
				<%@ include file="/common/_paginate.jsp"%>

			</div>
		</div>
	</div>
</body>
</html>