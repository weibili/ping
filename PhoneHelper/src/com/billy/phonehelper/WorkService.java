package com.billy.phonehelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

public class WorkService extends Service {

    static final int MSG_CALL = 1000;
    static final long DELAY = 60 * 1000;
    long lastCallTime = 0;
    ArrayList<String> callList = new ArrayList<String>();

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
            case MSG_CALL:
                if (callList.size() > 0) {
                    call(callList.remove(0));
                }
                if (callList.size() > 0) {
                    sendEmptyMessageDelayed(MSG_CALL, DELAY);
                }
                break;

            default:
                break;
            }
        };
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void startWork(Context context, Intent intent) {
        Intent i = new Intent(intent);
        i.setClass(context, WorkService.class);
        context.startService(i);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dealIntent(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void dealIntent(Intent intent) {
        String type = intent.getStringExtra("type");
        String number = intent.getStringExtra("number");
        Log.e("billy", String.format("onNewIntent:(type=%s  number=%s)", type, number));
        if (TextUtils.equals(type, "sms")) {
            String content = intent.getStringExtra("content");
            try {
                byte[] conBytes = Base64.decode(content.getBytes("utf-8"), Base64.DEFAULT);
                sendSms(number, new String(conBytes));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (TextUtils.equals(type, "call")) {
            callList.add(number);
            if (!mHandler.hasMessages(MSG_CALL)) {
                if (System.currentTimeMillis() - lastCallTime > DELAY) {
                    mHandler.sendEmptyMessage(MSG_CALL);
                } else {
                    mHandler.sendEmptyMessageDelayed(MSG_CALL, DELAY);
                }
            }
        }
    }

    void call(String number) {
        if (TextUtils.isDigitsOnly(number)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            lastCallTime = System.currentTimeMillis();
        } else {
            Toast.makeText(getApplicationContext(), "电话号码非法", Toast.LENGTH_SHORT).show();
        }
    }

    void sendSms(String number, String content) {
        if (TextUtils.isDigitsOnly(number)) {
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(getApplicationContext(), "短信内容为空", Toast.LENGTH_SHORT).show();
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                if (content.length() > 70) {
                    List<String> contents = smsManager.divideMessage(content);
                    for (String sms : contents) {
                        smsManager.sendTextMessage(number, null, sms, null, null);
                    }
                } else {
                    smsManager.sendTextMessage(number, null, content, null, null);
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "电话号码非法", Toast.LENGTH_SHORT).show();
        }
    }
}
