package com.demo.common.model;

import com.demo.common.model.base.BasePingrecord;
import com.jfinal.plugin.activerecord.Page;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Pingrecord extends BasePingrecord<Pingrecord> {
	public static final Pingrecord dao = new Pingrecord();
	public Page<Pingrecord> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from pingrecord order by firstPingTs,hostIp asc");
	}
	public Page<Pingrecord> paginate(int pageNumber, int pageSize,String hostIp,String hostName,String startDate,String endDate ) {
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
		return paginate(pageNumber, pageSize, "select * from pingrecord where 1=1 "+whereSql+" order by lastPingTs desc ");
	}
	public Page<Pingrecord> paginateSum(int pageNumber, int pageSize,String hostIp,String hostName,String startDate,String endDate ) {
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
		return paginate(pageNumber, pageSize, "select hostIp,hostName,count(*) as count,sum(downTime) as totalTime,avg(downTime) as avgTime from pingrecord where 1=1 "+whereSql+" group by hostIp,hostName order by hostIp,hostName ");
	}
}
