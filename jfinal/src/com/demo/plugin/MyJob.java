package com.demo.plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.demo.common.model.Host;
import com.demo.common.model.Manager;
import com.demo.common.model.Pingrecord;
import com.demo.tool.PhoneCaller;
import com.demo.tool.UUIDGenerator;

public class MyJob implements Job  {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		List<Host> hostList = Host.dao.find("select * from host where pingFlag='1'");
		List<Manager> mList = Manager.dao.find("select * from manager where useFlag='1'");
		if(hostList!=null&&hostList.size()>0){
			for(Host host :hostList){			
				if(ping(host.getHostIp(),2,100)){
					host.setHostStatus("1");
				}else{
					//IP状态为掉线
					if("0".equals(host.getHostStatus())){
						Pingrecord record =Pingrecord.dao.findById(host.getLastDownId());
						Date date = new Date();
						double hours =(date.getTime()-record.getFirstPingTs().getTime())/(60*60*1000);
						BigDecimal   b   =   new   BigDecimal(hours);  
						double downHours =b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  ;
						record.setLastPingTs(date);
						record.setDownTime(downHours);
						record.update();
					}else{
						Pingrecord record = new Pingrecord();
						String id = UUIDGenerator.getInstance().getUUID();
						record.setId(id);
						record.setHostIp(host.getHostIp());
						record.setHostName(host.getHostName());
						Date date = new Date();
						record.setFirstPingTs(date);
						record.setLastPingTs(date);
						record.setDownTime(0.03);
						record.save();
						host.setHostStatus("0");
						host.setLastDownId(id);
						//通知管理人员
						for(Manager m:mList){
							if("0".equals(m.getSendType())){
								PhoneCaller.sendSms(m.getTel(), m.getName()+"您好：设备IP["+host.getHostIp()+"]已经掉线，请处理。【自动通知】");
							}else{
								PhoneCaller.call(m.getTel());
							}
						}
					}					
				}				
				host.update();
			}
		}
	}
	public static boolean ping(String ipAddress, int pingTimes, int timeOut) {  
        BufferedReader in = null;  
        Runtime r = Runtime.getRuntime();  // 将要执行的ping命令,此命令是windows格式的命令  
       String pingCommand = "ping " + ipAddress + " -n " + pingTimes    + " -w " + timeOut;  
        try {   // 执行命令并获取输出  
           System.out.println(pingCommand);   
            Process p = r.exec(pingCommand);   
            if (p == null) {    
                return false;   
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
           int connectedCount = 0;   
            String line = null;   
            while ((line = in.readLine()) != null) {    
                connectedCount += getCheckResult(line);   
            }   // 如果出现类似=23ms TTL=62这样的字样,出现的次数=测试次数则返回真  
           return connectedCount == pingTimes;  
        } catch (Exception ex) {   
            ex.printStackTrace();   // 出现异常则返回假  
           return false;  
        } finally {   
            try {    
                in.close();   
            } catch (IOException e) {    
                e.printStackTrace();   
            }  
        }
    }
	//若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);  
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);  
         Matcher matcher = pattern.matcher(line);  
         while (matcher.find()) {
             return 1;
         }
         return 0; 
     }
}
