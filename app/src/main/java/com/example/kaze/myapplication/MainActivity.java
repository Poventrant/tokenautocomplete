package com.example.kaze.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static boolean isSend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button btn = (Button) findViewById(R.id.sendMail);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.emailText);
                if (!isSend) {
                    System.out.println("??????????????????????");
                    thread.start();
                } else {
                    Toast.makeText(MainActivity.this, "邮件已发送", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            //这个类主要是设置邮件
            MultiMailsender.MultiMailSenderInfo mailInfo = new MultiMailsender.MultiMailSenderInfo();
            mailInfo.setMailServerHost("smtp.163.com");
            mailInfo.setMailServerPort("25");
            mailInfo.setValidate(true);
            mailInfo.setUserName("pwq296306654@163.com");
            mailInfo.setPassword("pwq950317"); //许多邮箱使用smtp服务登录第三方软件需要使用授权码而不是密码
            mailInfo.setFromAddress("pwq296306654@163.com");
            mailInfo.setToAddress("296306654@qq.com");
            mailInfo.setSubject("test");
            mailInfo.setContent("test content");
            //这个类主要来发送邮件
            boolean isSuccess = false;
            SingleMailsender sms = new SingleMailsender();
//            isSuccess  = sms.sendTextMail(mailInfo);//发送文体格式
//            MultiMailsender.sendHtmlMail(mailInfo);//发送html格式
            isSuccess = sms.sendTextMail(mailInfo);//
            System.out.println(isSuccess);
            if (isSuccess) {
                isSend = true;
            }
        }
    });

}
