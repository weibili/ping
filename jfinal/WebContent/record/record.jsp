<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp"%>
<html xml:lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN">
<head>
<script type="text/javascript">
	$(function(){
		$('#startDate').datetimepicker();
		$('#endDate').datetimepicker();
	});
</script>
</head>
<body>
	<div class="manage_container">
		<%@ include file="/common/header.html"%>
		<div class="main">
			<h1>
				监控记录&nbsp;&nbsp; <a href="<%=path%>/record/deleteAll" onclick="return confirm('确定要删除吗？');">清除记录</a>
			</h1>
			<form action="<%=path%>/record/query" method="post" >
				<div class="form_box">
					<ul>
						<li style="border: none;"><span>设备IP:</span>
							<p>
								<input name="hostIp" type="text"/>
							</p>
						</li>
						
						<li><span>设备名称:</span>
							<p>
								<input name="hostName" type="text"/>
							</p>
						</li>
						<li><span>起始时间:</span>
							<p>
								<input name="startDate" type="text" id="startDate" value="${startDate}"/>
							</p>
						</li>
						<li><span>截至时间:</span>
							<p>
								<input name="endDate" type="text" id="endDate" value="${endDate}"/>
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
							<th width="25%">IP</th>
							<th width="24%">设备名称</th>
							<th width="24%">首次掉线时间</th>
							<th width="24%">最近掉线时间</th>
							<th width="24%">掉线小时（四舍五入）</th>
						</tr>
						<c:forEach items="${recordPage.list}" var="record">
							<tr>
								<td style="text-align: left;">${record.hostIp}</td>
								<td style="text-align: left;">${record.hostName}</td>
								<td style="text-align: left;">${record.firstPingTs}</td>
								<td style="text-align: left;">${record.lastPingTs}</td>
								<td style="text-align: left;">${record.downTime}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:set var="currentPage" value="${recordPage.pageNumber}" />
				<c:set var="totalPage" value="${recordPage.totalPage}" />
				<c:set var="cp" value="${pageContext.request.contextPath }"  />
				<c:set var="actionUrl" value="${cp}/record/" />
				<c:set var="urlParas" value="" />
				<%@ include file="/common/_paginate.jsp"%>

			</div>
		</div>
	</div>
</body>
</html>