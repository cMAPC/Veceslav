package com.example.devcolibri.taskv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebView;
import android.widget.TextView;

public class SecondActivity extends Activity{


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        String url;
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        url = b.getString("URL");
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl(url);


    }




}

