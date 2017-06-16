package com.landmaster.springboot.sdk;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.util.Map;

/**
 * Created by tdl on 2017/6/15.
 */
public class MyHttpGet {
    public String execute(Host rhost,SysParam param,Map<String,String> queryParam) throws IOException {

        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client=builder.build();
        String url=Util.toQueryUril(rhost.getRootPath(),param,queryParam);
        System.out.println(url);
        HttpRequest httpRequest = new HttpGet(url);
        httpRequest.setHeader("Content-Type","text/"+param.getFormat()+"; charset=UTF-8");
//        String  rs="<ns1:getUser xmlns:ns1=\"http://springboot.landmaster.com/\"><arg0>10002</arg0></ns1:getUser>";
//        ByteArrayInputStream requestEntity=new ByteArrayInputStream(rs.getBytes("UTF-8"));
//        entity = new InputStreamEntity(requestEntity);
//        ((HttpPost)httpRequest).setEntity(entity);
        HttpHost host= new HttpHost(rhost.getHost(),rhost.getPort(), "http");
        CloseableHttpResponse response=client.execute(host,httpRequest);
        InputStream inputStream=response.getEntity().getContent();
        InputStreamReader ir=new InputStreamReader(inputStream);
        BufferedReader br=new BufferedReader(ir);
        StringBuilder sbb=new StringBuilder();
        String line=null;
        while((line=br.readLine())!=null){
            sbb.append(line);
        }
        return sbb.toString();
    }
    public static void main(String[] args) throws IOException {
        Host rhost=new Host ();
        rhost.setHost("127.0.0.1");
        rhost.setPort(8081);
        rhost.setRootPath("/api");
        SysParam param =new SysParam ();
        param.setV("1.0");
        param.setSessionId("d36d02f769962d22ee8194e6d72ad3b5");
        param.setMethod("ttt");
        param.setFormat("json");
        param.setAppKey("44b7afd8702f44869dec97f56aa3842d");
        String secret="6251fccc18b74ae1847c10b1c649ca73";
        param.setSecret(secret);
        MyHttpGet get=new MyHttpGet();
        System.out.println("content:"+get.execute(rhost,param,null));
    }
}
