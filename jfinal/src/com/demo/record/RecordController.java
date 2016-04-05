package com.demo.record;

import java.util.ArrayList;
import java.util.List;

import com.demo.common.model.Pingrecord;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

/**
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class RecordController extends Controller {
	public void index() {
		setAttr("recordPage", Pingrecord.dao.paginate(getParaToInt(0, 1), 10));
		render("record.jsp");
	}
	public void query() {
		String hostIp = getPara("hostIp");
		String hostName =getPara("hostName");
		String startDate =getPara("startDate");
		String endDate =getPara("endDate");
		setAttr("hostIp",  hostIp==null?"":hostIp);
		setAttr("hostName",  hostName==null?"":hostName);
		setAttr("startDate",  startDate==null?"":startDate);
		setAttr("endDate",  endDate==null?"":endDate);
		
		setAttr("recordPage", Pingrecord.dao.paginate(getParaToInt(0, 1), 10,hostIp,hostName,startDate,endDate));
		render("record.jsp");
	}
	public void recordSum(){
		setAttr("recordPage", Db.paginate(getParaToInt(0, 1), 10, "select hostIp,hostName,count(*) as count,sum(downTime) as totalTime,round(avg(downTime),2) as avgTime from pingrecord  group by hostIp,hostName order by hostIp,hostName"));
		render("recordsum.jsp");
	}
	public void sumquery() {
		String hostIp = getPara("hostIp");
		String hostName =getPara("hostName");
		String startDate =getPara("startDate");
		String endDate =getPara("endDate");
		setAttr("hostIp",  hostIp==null?"":hostIp);
		setAttr("hostName",  hostName==null?"":hostName);
		setAttr("startDate",  startDate==null?"":startDate);
		setAttr("endDate",  endDate==null?"":endDate);
		String whereSql ="";
		if(hostIp!=null&&hostIp.length()>0){
			whereSql+=" and hostIp ='"+hostIp+"'";
		}
		if(hostName!=null&&hostName.length()>0){
			whereSql+=" and hostName ='"+hostName+"'";
		}
		if(startDate!=null&&startDate.length()>0){
			whereSql+=" and firstPingTs >=str_to_date('"+startDate+"','%Y-%m-%d %H:%i')";
		}
		if(endDate!=null&&startDate.length()>0){
			whereSql+=" and lastPingTs <=str_to_date('"+endDate+"','%Y-%m-%d %H:%i')";
		}
		String sql ="select hostIp,hostName,count(*) as count,sum(downTime) as totalTime,avg(downTime) as avgTime from pingrecord where 1=1 "+whereSql+" group by hostIp,hostName order by hostIp,hostName ";
		setAttr("recordPage", Db.paginate(getParaToInt(0, 1), 10,sql));
		render("recordsum.jsp");
	}
	public void deleteAll() {
		List<String> sqlList = new ArrayList<String>();
		sqlList.add("Delete from pingrecord");
		
		Db.batch(sqlList, 100);
		redirect("/record");
	}
}


