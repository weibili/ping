<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<fieldset class="solid">
	<legend>人员信息</legend>
	<input type="hidden" name="manager.id"
			value="${manager.id}" />
	<div>	
		<label>姓名*</label> <input type="text" name="manager.name"
			value="${manager.name}" />${nameMsg}
	</div>
	<div>
		<label>电话*</label> <input type="text" name="manager.tel"
			value="${manager.tel}" />${telMsg}
	</div>
	<div>
	 	<label>是否通知*</label> <select name="manager.useFlag">

      		<option value="1" selected="selected">是</option>

      		<option value="0" >否</option>

    	</select>${useMsg}
	</div>

	<div>
	 	<label >通知方式*</label> <select name="manager.sendType">

      		<option value="0" selected="selected">短信+电话</option>

    	</select>${sendTypeMsg}
	</div>

	<div>
		<label>&nbsp;</label> <input value="提交" type="submit">
	</div>
</fieldset>