package com.frain.myapplication.Http;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frain.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/11/14.
 */
public class HttpDemoActivity extends Activity {
    LinearLayout linearLayout;
    String[] datas = {"InetAddress", "URL", "GET","POST","LOGIN","UPLOAD IMAGE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        linearLayout = (LinearLayout) findViewById(R.id.root_linearlayout);
        for (int i = 0; i < datas.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            button.setText(datas[i]);
            button.setId(1000 + i);
            button.setOnClickListener(onClickListener);
            linearLayout.addView(button);
        }
        if(Build.VERSION.SDK_INT>=23){
            this.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},10001);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case 1000:
                    new Thread() {
                        @Override
                        public void run() {
                            demoInetAddress();
                        }
                    }.start();
                    break;
                case 1001:
                    new Thread() {
                        @Override
                        public void run() {
                            getData();
                        }
                    }.start();
                    break;
                case 1002:
                    new Thread() {
                        @Override
                        public void run() {
                            demoGet();
                        }
                    }.start();
                    break;
                case 1003:
                    new Thread() {
                        @Override
                        public void run() {
                            demoPost();
                        }
                    }.start();
                    break;
                case 1004:
                    new Thread() {
                        @Override
                        public void run() {
                            postLogin();
                        }
                    }.start();
                    break;
                case 1005:
                    new Thread() {
                        @Override
                        public void run() {
                            postImage();
                        }
                    }.start();
                    break;
            }
        }
    };

    //NetworkOnMainThreadException 在主线程中进行网络相关操作异常
    // 必须开启一个支线程来进行网络相关的操作
    public void demoInetAddress() {
        try {
            //将一个域名地址转换成InetAddress的对象
            InetAddress inetAddress = InetAddress.getByName("www.sina.com");
            Log.i("InetAddress", inetAddress.getHostAddress());//获取域名的ip地址
            Log.i("是否能够连接ip地址", "=====" + inetAddress.isReachable(1000));
        } catch (UnknownHostException e) {//无法解析域名异常
            e.printStackTrace();//打印异常堆栈信息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        try {
            //URL 访问网络资源的指针URL url=new URL(传入访问的接口地址)
            URL url = new URL("http://apis.baidu.com/tianyiweather/basicforecast/weatherapi");
            //通过URL对象获得本次连接的对象并强转成HttpURLConnection的对象
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();//调用连接方法，建立本次的HTTP请求的连接
            //获得请求返回的状态码得知是否连接成功
            //如果返回码为200则表示该次请求连接成功，可以进去读取输入流的操作
            //2xx 服务器已受理请求的返回码
            //3xx 连接重定向
            //4xx 页面不存在/服务器拒绝等问题
            //5xx 服务器的问题
            if (httpURLConnection.getResponseCode() == 200) {
                //通过连接对象获得输入流的对象
                InputStream inputStream = httpURLConnection.getInputStream();
                //字节流通向字符流的桥梁，设置对应的字符编码为UTF-8
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();//一个可变的字符序列，在读取流的时候，可以用缓存字符串
                String str;
                while ((str = bufferedReader.readLine()) != null) {//每次读一行直到读取到的数据为null则不继续读取
                    stringBuilder.append(str);//读取出来的每行字符添加到字符序列
                }
                Log.i("InputStream", stringBuilder.toString());
                String data = "{\n" +
                        "    \"list\": [\n" +
                        "        {\n" +
                        "            \"errNum\": 300202,\n" +
                        "            \"errMsg\": \"Missing apikey\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"errNum\": 300202,\n" +
                        "            \"errMsg\": \"Missing apikey\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"errNum\": 300202,\n" +
                        "            \"errMsg\": \"Missing apikey\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                try {
                    //解析Array
                    JSONObject jsonObject = new JSONObject(data);//
                    JSONArray jsonArray = jsonObject.optJSONArray("list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        long errNum = object.getLong("errNum");//通过key找到value
                        long errNum1 = object.optLong("errNum", -1);//通过key找到value如果没找到返回默认值
                        String errMsg = object.getString("errMsg");//...
                        Log.i("errNum" + i, "" + errNum);
                        Log.i("errMsg" + i, "" + errMsg);
                    }
                    //单独解析Object
                    //     String data=stringBuilder.toString();//将字符序列转换成字符串
                    //                    JSONObject jsonObject=new JSONObject(data);//
//                    long errNum=jsonObject.getLong("errNum");//通过key找到value
//                    long errNum1=jsonObject.optLong("errNum",-1);//通过key找到value如果没找到返回默认值
//                    String errMsg=jsonObject.getString("errMsg");//...
//                    Log.i("errNum",""+errNum);
//                    Log.i("errMsg",""+errMsg);

                } catch (JSONException e) {
                    e.printStackTrace();//打印堆栈信息
                }


            } else {//如果连接失败，则打印状态码
                Log.i("getResponseCode()", "" + httpURLConnection.getResponseCode());
            }

        } catch (IOException e) {//打开网络连接会抛出IO异常
            e.printStackTrace();
        }

    }













    //HTTP请求方式GET,POST
    //从访问方式的角度来看GET直接就是一串URL地址
    //GET :URL: http://apistore.baidu.com/astore/usercenter?menu=userInfo
    //     域名              /绝对地址/?拼接请求参数名menu值为userInfo的参数
    //POST: URL地址http://apistore.baidu.com/astore/usercenter
    //请求参数设置在requestPoperty中
    public void demoGet() {        //使用步骤
        //HttpURLConnection//专门用于HTTP连接的对象的类
        //这个类提供可以不知道数据的长度的情况下使用发送流/接受流的方式
        HttpURLConnection httpURLConnection = null;
        try {
            //URL 访问网络资源的指针URL url=new URL(传入访问的接口地址)
            String httpURl="http://apis.baidu.com/apistore/weatherservice/citylist";
            URL url = new URL(httpURl+"?"+"cityname="+ URLEncoder.encode("重庆","UTF-8"));
                                                      //将中文重庆转码成utf-8的字符编码格式
            //URLDecoder.decode("&#x91CD;&#x5E86;","UTF-8");//将数据解码成普通字符串的格式
            //1.通过URL对象获得本次连接的对象并强转成HttpURLConnection的对象
             httpURLConnection = (HttpURLConnection) url.openConnection();
            //2.设置相关的请求
            httpURLConnection.setRequestMethod("GET");//设置本次请求的方式
            httpURLConnection.setConnectTimeout(5000);//设置连接超时时候
            httpURLConnection.setRequestProperty("apikey","50657b78f0f72e081b5baf70a2dd3648");//设置请求参数到header中
            //3.设置是否需要输出流//GET方式不设置输出流
            //3.5建立连接
            httpURLConnection.connect();//建立本次连接
            //4.读取返回的参数，数据的长度，响应报头，session cookie
            if(httpURLConnection.getResponseCode()==200){//判断http请求响应码是否为200
                StringBuilder stringBuilder=new StringBuilder();
                InputStream inputStream=httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String s;
                while((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("data====>:",stringBuilder.toString());
                String data=stringBuilder.toString();
                //解析数据
                try{
                    JSONObject jsonObject=new JSONObject(data);
                    JSONArray retData=jsonObject.optJSONArray("retData");
                    int errNum=jsonObject.optInt("errNum",-1);
                    String errMsg=jsonObject.optString("errMsg","");
                    Log.i("errNum",""+errNum);
                    Log.i("errMsg",errMsg);
                    for(int i=0;i<retData.length();i++){
                        JSONObject obj=retData.getJSONObject(i);
                        String province_cn=obj.optString("province_cn","");
                        String district_cn=obj.optString("province_cn","");
                        String name_cn=obj.optString("name_cn","");
                        String name_en=obj.optString("name_en","");
                        String area_id=obj.optString("area_id","");
                        Log.i("province_cn",province_cn);
                        Log.i("district_cn",district_cn);
                        Log.i("name_cn",name_cn);
                        Log.i("name_en",name_en);
                        Log.i("area_id",area_id);
                    }


                }catch(JSONException e){
                    e.printStackTrace();
                }



            }else{
                Log.i("请求失败","状态码为:"+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpURLConnection!=null){
                //5.使用完连接对象之后要记得关闭该对象
                httpURLConnection.disconnect();
            }
        }

    }
    String params="{\n" +
            "\"params\": [\n" +
            "    {\n" +
            "      \"username\":\"test\",\n" +
            "      \"cmdid\":\"1000\",\n" +
            "      \"logid\": \"12345\",\n" +
            "      \"appid\": \"您的apikey\",\n" +
            "      \"clientip\":\"10.23.34.5\",\n" +
            "      \"type\":\"st_groupverify\",\n" +
            "      \"groupid\": \"0\",\n" +
            "      \"versionnum\": \"1.0.0.1\",\n" +
            "    }\n" +
            "  ],\n" +
            "  \"jsonrpc\": \"2.0\",\n" +
            "  \"method\": \"Delete\",\n" +
            "  \"id\":12\n" +
            "}";
    //POST方式传递请求参数的话，数据都是写入输出流
    //urlencode 【例子：name=value】 String params="username=furui&password=123"

    //form-data 表单数据 注意：http协议本身的原始方法不支持multipart/form-data请求
    //multipart/form-data的请求头必须包含一个特殊的头信息：Content-Type，
    // 且其值也必须规定为multipart/form-data，同时还需要规定一个内容分割符用于分割请求体中的多个post的内容，
    // 如文件内容和文本内容自然需要分割开来，不然接收方就无法正常解析和还原这个文件了。
    // httpURLConnection.setRequestProperty("Content-Type"," multipart/form-data; boundary="+boundary);
    //这里boundary=xxx ----xxx为formdata表单的分隔符

//    multipart/form-data的请求体也是一个字符串，
//    不过和post的请求体不同的是它的构造方式，post是简单的name=value值连接，
//    而multipart/form-data则是添加了分隔符等内容的构造体。具体格式如下:
//    --${bound}
//    Content-Disposition: form-data; name="username"
//
//    furui
//    --${bound}
//    Content-Disposition: form-data; name="password"
//
//    123456
//    --${bound}

//    Content-Disposition: form-data; name="file000"; filename="HTTP协议详解.pdf"
//    Content-Type: application/octet-stream
//
//    %PDF-1.5
//    file content
//    %%EOF
//
//    --${bound}

//    Content-Disposition: form-data; name="Upload"
//
//    Submit Query
//    --${bound}--//表单数据结尾还要附带--
    public void demoPost(){
        HttpURLConnection httpURLConnection=null;
        try {
            //URL 访问网络资源的指针URL url=new URL(传入访问的接口地址)
            String httpURl="http://apis.baidu.com/idl_baidu/faceverifyservice/face_deleteuser";
            URL url = new URL(httpURl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);//设置连接对象允许输出
            httpURLConnection.setDoInput(true);//................输入
            httpURLConnection.setUseCaches(false);//不适用缓存
            httpURLConnection.setRequestMethod("POST");//设置本次请求的方式
            httpURLConnection.setConnectTimeout(5000);//设置连接超时时间
            httpURLConnection.setRequestProperty("Content-Type","application/urlencode");
            httpURLConnection.setRequestProperty("apikey","50657b78f0f72e081b5baf70a2dd3648");//设置请求参数到header中
            OutputStream outputStream=httpURLConnection.getOutputStream();//获取输出流
            outputStream.write(params.getBytes("utf-8"));//将请求参数（bodyParams）写入到输出流当中
            outputStream.flush();//刷新
            outputStream.close();//关闭
            httpURLConnection.connect();//建立本次连接
            //4.读取返回的参数，数据的长度，响应报头，session cookie
            if(httpURLConnection.getResponseCode()==200){//判断http请求响应码是否为200
                StringBuilder stringBuilder=new StringBuilder();
                InputStream inputStream=httpURLConnection.getInputStream();//获得返回的数据流对象
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String s;
                while((s=bufferedReader.readLine())!=null){
                    stringBuilder.append(s);
                }
                Log.i("data====>:",stringBuilder.toString());
            }else{
                Log.i("请求失败","状态码为:"+httpURLConnection.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(httpURLConnection!=null){
                //5.使用完连接对象之后要记得关闭该对象
                httpURLConnection.disconnect();
            }
        }
    }
    HttpUtils httpUtils;
    public void postLogin(){
        httpUtils=new HttpUtils();
        Map<String,String> map=new  HashMap<String,String>();
        map.put("tel","18716341029");
        map.put("password","19831004");
        httpUtils.postData("http://211.149.190.90/api/login",map);
    }
    public void postImage(){
        Map<String,String> map=new  HashMap<String,String>();
        map.put("tel","18716341029");
        map.put("uid",httpUtils.uid);
        map.put("token",httpUtils.token);
        InputStream inputStream=null;
        try {
            inputStream=getAssets().open("img.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpUtils.uploadImage("http://211.149.190.90/api/uploads",map,inputStream);
    }


}
