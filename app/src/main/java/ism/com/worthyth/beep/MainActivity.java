package ism.com.worthyth.beep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import ism.com.worthyth.beep.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("MODIFICATION MOT DE PASSE");
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://gebumas.com/beeppay/androidpassforgot.php");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
