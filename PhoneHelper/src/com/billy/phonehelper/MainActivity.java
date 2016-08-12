package com.billy.phonehelper;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText mEtNumber;
    private EditText mEtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtNumber = (EditText) findViewById(R.id.et_number);
        mEtContent = (EditText) findViewById(R.id.et_content);
        findViewById(R.id.btn_call).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String number = mEtNumber.getText().toString();
                call(number);
            }
        });
        findViewById(R.id.btn_send).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String number = mEtNumber.getText().toString();
                String content = mEtContent.getText().toString();
                sendSms(number, content);
            }
        });
        dealIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dealIntent(intent);
    }

    private void dealIntent(Intent intent) {
//        String type = intent.getStringExtra("type");
//        String number = intent.getStringExtra("number");
//        Log.e("billy", String.format("onNewIntent:(type=%s  number=%s)", type, number));
//        boolean flag = false;
//        if (TextUtils.equals(type, "sms")) {
//            String content = intent.getStringExtra("content");
//            sendSms(number, content);
//            flag = true;
//        } else if (TextUtils.equals(type, "call")) {
//            call(number);
//            flag = true;
//        }
//        if (flag) {
//            mEtNumber.postDelayed(new Runnable() {
//                
//                @Override
//                public void run() {
//                    finish();
//                }
//            }, 2000);
//        }
        WorkService.startWork(getApplicationContext(), intent);
    }

    void call(String number) {
        if (TextUtils.isDigitsOnly(number)) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
