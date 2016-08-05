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
				统计查询&nbsp;&nbsp;
			</h1>
			<form action="<%=path%>/record/sumquery" method="post" >
				<div class="form_box">
					<ul>
						<li style="border: none;"><span>IP地址:</span>
							<p>
								<input name="hostIp" type="text"/>
							</p>
						</li>
						
						<li><span>链路名称:</span>
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
							<th width="24%">链路名称</th>
							<th width="24%">掉线次数</th>
							<th width="24%">掉线总小时</th>
							<th width="24%">单次掉线平均小时</th>
						</tr>
						<c:forEach items="${recordPage.list}" var="record">
							<tr>
								<td style="text-align: left;">${record.hostIp}</td>
								<td style="text-align: left;">${record.hostName}</td>
								<td style="text-align: left;">${record.count}</td>
								<td style="text-align: left;">${record.totalTime}</td>
								<td style="text-align: left;">${record.avgTime}</td>
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