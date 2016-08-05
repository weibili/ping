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
				链路管理&nbsp;&nbsp; <a href="<%=path%>/host/add">添加链路</a>
			</h1>
			<form action="<%=path%>/host/query" method="post" >
				<div class="form_box">
					<ul>
						<li style="border: none;"><span>IP地址:</span>
							<p>
								<input name="hostIp" type="text" value="${hostIp}"/>
							</p>
						</li>
						
						<li><span>链路名称:</span>
							<p>
								<input name="hostName" type="text"  value="${hostName}"/>
							</p>
						</li>
					</ul>
					<div class="search_button">
       					<input type="submit" value="查询" />
       				</div>
				</div>
			</form>
			<div class="table_box">
				<table class="list">
					<tbody>
						<tr>
							<th width="3%">地址IP</th>
							<th width="10%">链路名称</th>
							<th width="4%">链路状态</th>
							<th width="4%">是否启动监控</th>
							<th width="3%">操作</th>
						</tr>
						<c:forEach items="${hostPage.list}" var="host">
							<tr>
								<td style="text-align: left;">${host.hostIp}</td>
								<td style="text-align: left;">${host.hostName}</td>
								<td style="text-align: left;">${(host.hostStatus)=='0'?'<font color="green">正常</font>':'<font color="red">掉线</font>'}</td>
								<td style="text-align: left;">${(host.pingFlag)=='0'?'否':'是'}</td>
								<td style="text-align: center;">&nbsp;&nbsp;<a
									href="<%=path%>/host/delete?ip=${host.hostIp}">删除</a>
									&nbsp;&nbsp;<a href="<%=path%>/host/edit?ip=${host.hostIp}">修改</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:set var="currentPage" value="${hostPage.pageNumber}" />
				<c:set var="totalPage" value="${hostPage.totalPage}" />
				<c:set var="cp" value="${pageContext.request.contextPath }" />
				<c:set var="actionUrl" value="${cp}/host/" />
				<c:set var="urlParas" value="" />
				<%@ include file="/common/_paginate.jsp"%>

			</div>
		</div>
	</div>
</body>
</html>