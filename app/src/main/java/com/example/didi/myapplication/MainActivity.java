package com.example.didi.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private WebView wv;
    private ProgressBar pb;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        wv = (WebView) findViewById(R.id.wv);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.invokeZoomPicker();
        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress){
                pb.setProgress(progress);
                pb.setVisibility(progress < 100 ? View.VISIBLE : View.GONE);
            }
        });
//        wv.loadUrl("http://www.jd.com");
        wv.loadUrl("https://webkit.org/");
    }

    @Override
    public void onBackPressed(){
        WebView wv = (WebView) findViewById(R.id.wv);
        if(wv.canGoBack()){
            wv.goBack();
            return;
        }
        super.onBackPressed();
    }

}
