package com.frain.myapplication.WebView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.frain.myapplication.R;

/**
 * Created by admin on 2016/11/21.
 */
public class WebViewDemoActivity extends Activity{
    WebView webView;//网页视图 打开网页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_demo);
        webView=(WebView)findViewById(R.id.webview);
        String data="<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html>\n" +
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                "<head>\n" +
                "<title>文档标题</title>\n" +
                "</head>\n" +
                "\n" +
                "<script>\n" +
                "<!-- 本地网页js脚本 带参方法>\n" +
                "function insert(string){\n" +
                "    document.getElementById(\"content\").innerHTML += string + \"<br/>\";//在content标签段落加入新行\n" +
                "}\n" +
                "\n" +
                "function showAndroidToast() \n" +
                "{\n" +
                "    var name = document.getElementById(\"username\").value;//获取输入框的内容\n" +
                "    window.demo.showToast(name);//将输入内容传给android提示  demo是绑定对象别名\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "<body>\n" +
                "    <div>  \n" +
                "        <p>输入用户账号：</p>\n" +
                "        <input id=\"username\" style=\"height:30px;width:200px\"/> \n" +
                "    </div>\n" +
                "    <br>\n" +
                "    <div> \n" +
                "         <input type=\"submit\" value=\"提交\" style=\"height:51px;width:200px\" onclick=\"showAndroidToast()\"/>\n" +
                "    </div>\n" +
                "    <p id=\"content\"></p>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>\n";
        //直接加载网页数据
        //网页资源你的字符串数据,网页数据的类型,内容的文字编码
        // webView.loadData(String data,String mineType,String encoding);
     //   webView.loadData(data,"text/html; charset=utf-8",null);
        //直接加载网页   如果不设置，那么将会跳转到浏览器
     //   webView.loadUrl("http://www.baidu.com");
     //     webView.loadUrl("file:///android_asset/changecity.html");
     //   注意事项
//        1- AndroidManifest.xml中必须使用许可"android.permission.INTERNET",否则会出错。
//        2- 如果访问的页面中有Javascript，则webview必须设置支持Javascript。
//        webview.getSettings().setJavaScriptEnabled(true);
//        3- 如果页面中链接，如果希望点击链接继续在当前browser中响应，
//        而不是新开Android的系统browser中响应该链接，必须覆盖 webview的WebViewClient对象。

         webView.setWebViewClient(new MyWebClient());//设置自己带的webClient将在本页加载网页数据
         webView.getSettings().setJavaScriptEnabled(true);//支持js脚本
       // webView.addJavascriptInterface(Object obj,String name);
                  //对应js需要调用的方法的类，类的别名【js中直接使用别名来找到这个类，从而调用指定类的方法】
         webView.addJavascriptInterface(new WebViewInterface(this),"demo");
         webView.loadUrl("file:///android_asset/index.html");
    }
    int i=1000;
    public void insertClick(View view){
        webView.loadUrl("javascript:insert("+(i++)+")");//调用网页的js函数
    }

    public class WebViewInterface{
        Context context;
        public WebViewInterface(Context context){
            this.context=context;
        }
        //方法名必须与js中的方法名一致
        @JavascriptInterface//必须加上【注解标识的公有方法可以被JS代码访问】
        public void showToast(String name)
        {
            Toast.makeText(context,name,Toast.LENGTH_LONG).show();
        }
    }































    //按键点击监听回调
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK  && webView.canGoBack()){
            //如果点击的按钮是返回键的话 且浏览器能返回上一页
            //浏览器回到上一页
            webView.goBack();
        }
        return super.onKeyDown(keyCode, event);
    }
    //
    class MyWebClient extends WebViewClient{
        //执行页面加载之前的回调函数
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.i("shouldOverrideUrlLoad","request"+request);
            return super.shouldOverrideUrlLoading(view, request);
        }
        //页面开始加载监听
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i("onPageStarted","url"+url+"favicon"+favicon);
            super.onPageStarted(view, url, favicon);
        }
        //页面加载结束监听  此时页面加载完成但是图片可能还在加载中
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.i("onPageFinished","url"+url);
            super.onPageFinished(view, url);
        }
        //加载网页资源的时候调用
        @Override
        public void onLoadResource(WebView view, String url) {
            Log.i("onLoadResource","url"+url);
            super.onLoadResource(view, url);
        }

        //通知主机应用程序WebView内容遗留下来的前一页导航将不再。
        @Override
        public void onPageCommitVisible(WebView view, String url) {
            Log.i("onPageCommitVisible","url"+url);
            super.onPageCommitVisible(view, url);
        }
        //应用程序加载访问网页而返回得到的数据
       @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
           Log.i("shouldInterceptRequest","request"+request);
           return super.shouldInterceptRequest(view, request);
        }
        //访问某个网页返回错误是回调
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Log.i("onReceivedError","request"+request+"error"+error);
            super.onReceivedError(view, request, error);
        }
        //http请求错误返回
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Log.i("onReceivedError","request"+request+"errorResponse"+errorResponse);
            super.onReceivedHttpError(view, request, errorResponse);
        }
        //接受访问安全连接的错误
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            Log.i("onReceivedSslError","handler"+handler+"error"+error);
            super.onReceivedSslError(view, handler, error);
        }
        //如果浏览器重新发送数据请求某个页面时，默认是不重新发送请求
        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            Log.i("onReceivedError","dontResend"+dontResend+"resend"+resend);
            super.onFormResubmission(view, dontResend, resend);
        }
        //通知浏览器更新链接到数据库
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            Log.i("doUpdateVisitedHistory","url"+url+"isReload"+isReload);
            super.doUpdateVisitedHistory(view, url, isReload);
        }
        //通知主机来处理ssl客户证书的请求
        @Override
        public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
            Log.i("onReceivedClientCertRe","request"+request);
            super.onReceivedClientCertRequest(view, request);
        }
        //接收webview对应http身份验证的请求，
        //host 身份认证的主机
        //realm 一种描述用来帮助存储用户凭据以为将来访问所使用
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            Log.i("onReceivedHttpAuth","handler"+handler+"host"+host+"realm"+realm);
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }
        //通知主机应用程序输入事件不是由WebView处理。
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            Log.i("shouldOverrideKeyEvent","event"+event);
            return super.shouldOverrideKeyEvent(view, event);
        }
        /**
         * 当尺寸大小改变
         * oldScale  就的缩放比
         * newScale 新的缩放比
         */
        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            Log.i("onScaleChanged","oldScale"+oldScale+"newScale"+newScale);
            super.onScaleChanged(view, oldScale, newScale);
        }
        /**
         * 接收到登陆请求，也只有服务起要求登陆的时候，才会回调此方法
         * realm 一种描述用来帮助存储用户凭据以为将来访问所使用
         * account 账户
         * args 用于登录的用户的认证的具体参数
         */
        @Override
        public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
            Log.i("onReceivedLoginRequest","realm"+realm+"account"+account+"args"+args);
            super.onReceivedLoginRequest(view, realm, account, args);
        }
    }
}
