package com.cxmax.multiprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cxmax.webprogress.ui.RemoteAccountWebActivity;
import com.cxmax.webprogress.ui.RemoteCommonWebActivity;
import com.cxmax.webprogress.view.DWebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.openWeb1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteCommonWebActivity.start(MainActivity.this, "百度一下", "http://baidu.com");
            }
        });

        findViewById(R.id.openWeb2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for baselevel
                RemoteCommonWebActivity.start(MainActivity.this, "AIDL测试", DWebView.CONTENT_SCHEME + "aidl.html");

                // for account level
//                RemoteAccountWebActivity.start(MainActivity.this, "AIDL测试", DWebView.CONTENT_SCHEME + "aidl.html");
            }
        });
    }
}
