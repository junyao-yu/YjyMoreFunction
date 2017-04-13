package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.base.JavaScriptInterface;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2017/4/12 下午3:41
 * Email：yujunyao@yonglibao.com
 */


public class WebActivity extends BaseActivity {
    @Bind(R.id.webview)
    WebView webview;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getHtml();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

            }
        });
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view,String url,String message,JsResult result){
                return super.onJsAlert(view,url,message,result);
            }});
        webview.addJavascriptInterface(new JavaScriptInterface(webview), "android");


        webview.loadUrl("http://www.jcodecraeer.com/member/index.php");
    }

    private void getHtml() {
        webview.loadUrl("javascript:window.android.getHTML('<html>'+document.body.innerHTML+'</html>');");
    }

    @OnClick({R.id.btn_get, R.id.btn_add_click_event})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                webview.loadUrl("javascript:window.android.displayPassword(document.getElementById('" + JavaScriptInterface.id + "').value)");
                break;
            case R.id.btn_add_click_event:
//                webview.loadUrl("javascript:" + "document.getElementById(\"btnSignCheck\").addEventListener(\"click\", function(){\n" +
//                    "    alert(11111);\n" +
//                    "})");

                webview.loadUrl("javascript:" + "function aa() {\n" +
                        "\t\tdocument.getElementById(\"btnSignCheck\").addEventListener(\"click\", function(){\n" +
                        "    \t\twindow.android.displayPassword(document.getElementById('" + JavaScriptInterface.id + "').value)\n" +
                        "\t\t});\n" +
                        "\t}");

                webview.loadUrl("javascript:aa();");
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_web;
    }

}
