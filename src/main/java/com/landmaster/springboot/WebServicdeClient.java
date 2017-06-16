package com.landmaster.springboot;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.ws.BindingProvider;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tdl on 2017/6/2.
 */
public class WebServicdeClient {
    public static void main(String[] args) throws Exception {
        //{"access_token":"1f53d7d2bebd8d0687bd89c0b94d3d05","token_type":"Bearer","expires_in":43199}INFO  IgniteCacheService -  add access log event:PROCESS_SUCCESS
        //secret
        //----------------------------valueMap={method=webservicetest, v=1, appKey=44b7afd8702f44869dec97f56aa3842d, sessionId=40b796c0ed0b72ea55f94d2025951b57}
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        String sessoinId="063a088ebf13c6049d971d6d086f221e";
        Map<String,String > map=new HashMap<String,String>();
        map.put("method","webservicetest");
        map.put("v","1");
        map.put("format","soap");
        map.put("appKey","44b7afd8702f44869dec97f56aa3842d");
        map.put("sessionId",sessoinId);
        String    sign=SecretUtils.sign(map,"6251fccc18b74ae1847c10b1c649ca73");
        System.out.println("--------------------sign:"+sign);
        // Client client = dcf.createClient("http://localhost:8082/soap/user?wsdl");
        try {
            Client client = dcf.createClient("http://localhost:8081/api?method=webservicetest&format=soap&wsdl&appKey=44b7afd8702f44869dec97f56aa3842d&v=1" +
                    "&sessionId="+sessoinId+"&sign=" + sign);
            System.out.println("000000000000000000000000000bus");
            System.out.println(client.getBus());
            System.out.println("000000000000000000000000000endpoint");
            System.out.println(client.getEndpoint());
            System.out.println("000000000000000000000000000endpointend");

            System.out.println(client.getEndpoint().getEndpointInfo());
            System.out.println(client.getEndpoint().getEndpointInfo().getAddress());
            client.getEndpoint().getEndpointInfo().setAddress("http://localhost:8081/api?method=webservicetest&format=soap&appKey=44b7afd8702f44869dec97f56aa3842d&v=1" +
                    "&sessionId="+sessoinId+"&sign=" + sign);
            System.out.println("================sssssssssssssssssssss:"+client.getEndpoint().getService().getClass().getCanonicalName());
            System.out.println("================sssssssssssssssssssss:"+client.getEndpoint().getService());
            System.out.println("================sssssssssssssssssssss:"+client.getEndpoint().getClass());

            Object[] objects = client.invoke("getUser", 10002L);
            System.out.println(objects[0].getClass());
            System.out.println(((User) objects[0]).getUserId());
        }catch (Exception e){
                e.printStackTrace();
        }
    }
}
