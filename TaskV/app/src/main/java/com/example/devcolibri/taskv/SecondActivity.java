package com.example.devcolibri.taskv;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class SecondActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        WebView myWebView = (WebView) findViewById(R.id.webview);

        String url = getIntent().getStringExtra("URL");
        myWebView.loadUrl(url);
    }
}

