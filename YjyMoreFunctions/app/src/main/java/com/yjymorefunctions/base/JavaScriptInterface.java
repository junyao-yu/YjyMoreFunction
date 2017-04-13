package com.yjymorefunctions.base;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Auth：yujunyao
 * Since: 2017/4/12 下午4:12
 * Email：yujunyao@yonglibao.com
 */


public class JavaScriptInterface {

    private WebView webView;
    public static String id = "";

    public JavaScriptInterface(WebView webView) {
        this.webView = webView;
    }

    @JavascriptInterface
    public void getHTML(final String html) {
        if (!TextUtils.isEmpty(html)) {
            Log.e("html--->", html);
            parserHtml(html);
//            webView.loadUrl("javascript:" + "document.getElementById(\"btnSignCheck\").addEventListener(\"click\", function(){\n" +
//                    "    alert(11111);\n" +
//                    "})");

            webView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:" + "function aa() {\n" +
                            "\t\tdocument.getElementById(\"btnSignCheck\").addEventListener(\"click\", function(){\n" +
                            "    \t\twindow.android.displayPassword(document.getElementById('" + JavaScriptInterface.id + "').value)\n" +
                            "\t\t});\n" +
                            "\t}");

                    webView.loadUrl("javascript:aa();");
                }
            }, 2_000);

        }
    }

    @JavascriptInterface
    public void displayPassword(final String password) {
        Toast.makeText(webView.getContext(), password, Toast.LENGTH_LONG).show();
    }

    public void parserHtml(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.select("input");

        boolean isPassword = false;

        for (Element element : elements) {
            String type = element.attr("type");
            if (type.equals("password")) {
                isPassword = true;
                break;
            }
        }

        if (!isPassword) {
            return;
        }

        for (Element element : elements) {
            String name = element.className();
            String type = element.attr("type");
            String id = element.attr("id");
            if (type.equals("password")) {
                Log.e("pwd---id--->", id);
                this.id = id;
            }
        }

    }

}
