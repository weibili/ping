package com.demo.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class PhoneCaller {

	static final String ADB = "c:\\adb\\adb.exe";
	static long lastCmdTime = 0;

	public static void main(String[] args) {
        // call("10010");
        sendSms("18602170470", "test");
    }

    /**
     * 打电话
     * 
     * @param number
     *            电话号码
     */
	public static void call(String number) {
		//TODO 必须明确给出adb的绝对路径
        String cmd = String
                .format("%s shell am start -n com.billy.phonehelper/.MainActivity -e type call -e number %s", 
                        new String[] { ADB, number });
        doCommand(cmd);
    }

    /**
     * 发短信
     * 
     * @param number
     *            电话号码
     * @param content
     *            短信内容
     */
    public static void sendSms(String number, String content) {
		content = Base64.encode(content.getBytes(Charset.forName("utf-8")));
		//TODO 必须明确给出adb的绝对路径
        String cmd = String
                .format("%s shell am start -n com.billy.phonehelper/.MainActivity -e type sms -e number %s -e content %s",
                        new String[] { ADB, number, content });
        doCommand(cmd);
    }

    private static void doCommand(String cmd) {
        String s = null;
        try {
			if (System.currentTimeMillis()-lastCmdTime > 120 * 1000) {
                Runtime.getRuntime().exec(String.format("%s shell input keyevent 26", ADB));
            }
            lastCmdTime = System.currentTimeMillis();
        	System.out.println(cmd);
        	Runtime r = Runtime.getRuntime();
        	Process p = r.exec(cmd);   
        			

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
        }
    }

}
