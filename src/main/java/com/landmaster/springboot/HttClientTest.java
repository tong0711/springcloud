package com.landmaster.springboot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by tdl on 2017/6/6.
 */
public class HttClientTest {
    public static void main(String[] args) throws IOException {
        Map<String,String > map=new HashMap<String,String>();
        //{"access_token":"5b9b19fa2f7bd86f026e905f37562a5e","token_type":"Bearer","expires_in":43199}
        //{"access_token":"822f536eefa9979206697acb4ffa1e57","token_type":"Bearer","expires_in":43199}
        //{"access_token":"453861a09cba3c3b87666f81e6043fef","token_type":"Bearer","expires_in":15569}
        String sessionid="453861a09cba3c3b87666f81e6043fef";
        map.put("method","wms_item_inf");
        map.put("v","1.0");
        map.put("format","xml");
        map.put("appKey","44b7afd8702f44869dec97f56aa3842d");
        map.put("sessionId",sessionid);
        //{usercd}/{password}/{omsinfo}
        map.put("usercd","u");
        map.put("password","p");
         map.put("omsinfo","o");
        String    sign=SecretUtils.sign(map,"6251fccc18b74ae1847c10b1c649ca73");
        System.out.println("--------------------sign:"+sign);
        String url=
                "/api?method=wms_item_inf&format=xml&appKey=44b7afd8702f44869dec97f56aa3842d&v=1.0" +
                "&sessionId="+sessionid+"&sign=" + sign+"&usercd=u&password=p&omsinfo=o";
        System.out.println("http://127.0.0.1:8083"+url);
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client=builder.build();
        InputStreamEntity entity=null;
        HttpRequest httpRequest=null;
        //System.out.println("------------uri query:"+uri+getQueryString());


        httpRequest = new HttpPost(url);
        httpRequest.setHeader("Content-Type","text/xml; charset=UTF-8");
//        String  rs="<ns1:getUser xmlns:ns1=\"http://springboot.landmaster.com/\"><arg0>10002</arg0></ns1:getUser>";
//        ByteArrayInputStream requestEntity=new ByteArrayInputStream(rs.getBytes("UTF-8"));
//        entity = new InputStreamEntity(requestEntity);
//        ((HttpPost)httpRequest).setEntity(entity);
        HttpHost   host= new HttpHost("127.0.01", 8083, "http");
        CloseableHttpResponse response=client.execute(host,httpRequest);
        System.out.println("===================== soap body:");
        response.getEntity().writeTo(System.out);
        int ava=response.getEntity().getContent().available();
        byte[]  datas=new byte[ava];
        System.out.println(new String(datas,"UTF-8"));

//        map=new HashMap<String,String>();
//        map.put("method","interface1");
//        map.put("v","1.0");
//        map.put("format","xml");
//        map.put("appKey","44b7afd8702f44869dec97f56aa3842d");
//        map.put("sessionId",sessionid);
//        sign=SecretUtils.sign(map,"6251fccc18b74ae1847c10b1c649ca73");
//        url=   "/api?method=interface1&format=xml&appKey=44b7afd8702f44869dec97f56aa3842d&v=1.0" +
//                "&sessionId="+sessionid+"&sign=" + sign;
//        httpRequest = new HttpGet(url);
//        httpRequest.setHeader("Content-Type","text/xml; charset=UTF-8");
//        response=client.execute(host,httpRequest);
//        System.out.println("===============================get getget ");
//        response.getEntity().writeTo(System.out);
        //client.execute(httpRequest);


    }
}
