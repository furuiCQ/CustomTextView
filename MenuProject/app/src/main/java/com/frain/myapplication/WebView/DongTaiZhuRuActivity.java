package com.frain.myapplication.WebView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/22.
 */
public class DongTaiZhuRuActivity extends Activity{
    //动态注入js
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_demo);
        webView=(WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);//设置支持网页代码的执行
        webView.setWebViewClient(new MyWebViewClient());
        webView.addJavascriptInterface(new MyJsInterface(this),"jsInterface");//支持js接口
        webView.loadUrl("http://www.12365auto.com/news/2014-07-10/20140710115457.shtml");

    }
    //1.找到搜索控件  【网页加载完成以后找到搜索控件】
    //2.为搜索控件绑定新的事件
    //3.点击搜索以后，showToast()
    public class MyJsInterface{
        Context context;
        public MyJsInterface(Context context){
        this.context=context;
        }
        @JavascriptInterface//API17以上必须加，否则找不到这个方法，无法调用
        public void showToast(String  content){
            Toast.makeText(context,content,Toast.LENGTH_LONG).show();
        }
        @JavascriptInterface //sdk 17以上一定要加上注解 否则找不到该方法
        public void jsInvokeJava(String img) {
            Log.i("songe", "被点击的图片地址为：" + img); //此时拿到了图片的绝对地址 下载下来进行放大预览等操作都可以；
        }
    }
    public class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
           Log.i("onPageFinished","onPageFinished");
            super.onPageFinished(view, url);
            //搜索id ssan
            //输入框的id  txtsearch
            //找到搜索按钮给予绑定点击事件，
            view.loadUrl("javascript:(" +
                    "function(){" +
                    "document.getElementById(\"btn_submit\").onclick=" +
                    "function(){" +
                    "var input=document.getElementById(\"txtsearch\").value;" +
                    "window.jsInterface.showToast(input);" +
                    "}"  +
                    "})");
//            view.loadUrl(
//                    "javascript:(function(){"
//                            + "  var objs = document.getElementsByTagName(\"img\"); "
//                            + "  for(var i=0;i<objs.length;i++){"
//                            + "     objs[i].onclick=function(){"
//                            +
//                            "var input=document.getElementById(\"txtsearch\").value;" +
//                            "window.jsInterface.showToast(input);"
//                            + "     }"
//                            + "  }"
//                            + "})");
//

        }
    }
}
