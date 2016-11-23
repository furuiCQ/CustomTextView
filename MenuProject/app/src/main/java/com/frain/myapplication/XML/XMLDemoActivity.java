package com.frain.myapplication.XML;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frain.myapplication.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by admin on 2016/11/21.
 */
public class XMLDemoActivity extends Activity {
    String[] datas = {"SAX", "DOM", "PULL"};
    LinearLayout rootLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
        rootLinearLayout = (LinearLayout) findViewById(R.id.root_linearlayout);
        for (int i = 0; i < datas.length; i++) {
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setText(datas[i]);
            button.setId(1000 + i);
            button.setOnClickListener(clickListener);
            rootLinearLayout.addView(button);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case 1000:
                    saxDemo();
                    break;
                case 1001:
                    DomDemo();
                    break;
                case 1002:
                    break;
            }
        }
    };
    ArrayList<Person> persons=new ArrayList<Person>();

    public void saxDemo() {
        try {
            InputStream inputStream = this.getAssets().open("person.xml");
            //创建一个Sax解析工程对象对象
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            //创建一个解析对象
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //创建一个解析监听回调函数
            MySaxHandler mySaxHandler = new MySaxHandler(persons);
            //绑定监听，并解析对应的流
            saxParser.parse(inputStream, mySaxHandler);
            persons=mySaxHandler.getData();
            for(int i=0;i<persons.size();i++){
                Person person=persons.get(i);
                Log.i("person.id",""+person.id);
                Log.i("person.age",""+person.age);
                Log.i("person.name",""+person.name);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public  void DomDemo(){
        try {
            InputStream inputStream = this.getAssets().open("person.xml");
            //创建一个DomBuidlerFactory的对象
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            // //创建一个dom解析工程对象对象 //该类用于解析一个xml数据流为一个Dom对象
            DocumentBuilder documentBuilder=factory.newDocumentBuilder();
            //解析返回Dom对象
            Document document=documentBuilder.parse(inputStream);
            //获得元素对象
            Element element=document.getDocumentElement();
            //获得指定名称的根节点
            NodeList list=element.getElementsByTagName("person");
            for (int i=0;i<list.getLength();i++){
                Element ele=(Element) list.item(i);//获得Person节点的对象
               // ele.getAttribute("id");
                Person person=new Person();
                person.setId(Integer.parseInt(ele.getAttribute("id")));//获得id属性的值
                NodeList sublist=ele.getChildNodes();//获取person节点下的子节点
                for (int j=0;j<sublist.getLength();j++) {//遍历person下的所有子节点
                    Node node=sublist.item(j);//获得每一个元素对象
                    if(node.getNodeType() ==Node.ELEMENT_NODE){//判断是否为元素对象
                        Element childNode = (Element) node;//强转
                        if(childNode.getNodeName().equals("name")){//查看元素名称是否为name
                            person.setName(childNode.getFirstChild().getNodeValue());//通过节点找到对应的值
                        }
                        if(childNode.getNodeName().equals("age")){//查看元素名称是否为name
                            person.setAge(Short.parseShort(childNode.getFirstChild().getNodeValue()));
                        }
                    }
                }
                persons.add(person);
            }
            for(int i=0;i<persons.size();i++){
                Person person=persons.get(i);
                Log.i("person.id",""+person.id);
                Log.i("person.age",""+person.age);
                Log.i("person.name",""+person.name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public void PullDemo(){

    }


}
