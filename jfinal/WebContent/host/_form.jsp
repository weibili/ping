<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<fieldset class="solid">
	<legend>链路信息</legend>
	<div>
		<label>IP地址*</label> <input type="text" name="host.hostIp"
			value="${host.hostIp}" />${ipMsg}
	</div>
	<div>
		<label>链路名称</label> <input type="text" name="host.hostName"
			value="${host.hostName}" />
	</div>
	<div>
	 	<label>是否启动监控*</label> <select name="host.pingFlag">

      		<option value="1" selected="selected">是</option>

      		<option value="0" >否</option>

    	</select>${titleMsg}
	</div>
	<div>
		<label>&nbsp;</label> <input value="提交" type="submit">
	</div>
</fieldset>