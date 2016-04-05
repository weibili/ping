package com.demo.tool;

import java.util.UUID;

public class UUIDGenerator {
	private UUIDGenerator(){
		
	}
	private static UUIDGenerator instance=null;
	public static UUIDGenerator getInstance(){
		if(instance == null){
			synchronized (UUIDGenerator.class) {
				if(instance ==null){
					instance = new UUIDGenerator();
				}
			}
		}
		return instance;
	}
	public synchronized String getUUID(){
		String s = UUID.randomUUID().toString(); 
	    //去掉“-”符号 
	    return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
}
