package com.landmaster.springboot.sdk.test;

import com.landmaster.springboot.sdk.Host;
import com.landmaster.springboot.sdk.MyHttpGet;
import com.landmaster.springboot.sdk.MyHttpPost;
import com.landmaster.springboot.sdk.SysParam;
import com.landmaster.springboot.sdk.preset.Token;
import com.landmaster.springboot.sdk.preset.TokenAction;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tdl on 2017/6/16.
 */
public class Tester {
    public String  getTest(Host  host,SysParam param) throws IOException {
        param.setV("1.0");
        param.setMethod("ttt");
        param.setFormat("json");
        MyHttpGet get=new MyHttpGet();
        return get.execute(host,param,null);

    }
    public String  postTest(Host  host,SysParam param) throws IOException {

        param.setV("1.0");
        param.setMethod("posttest");
        param.setFormat("json");
        MyHttpPost post=new MyHttpPost();
        Map<String,String> qm=new HashMap();
        qm.put("param1","11");
        qm.put("param2","22");
        return post.execute(host,param,qm,"{param1:'11',param2:'22}");
    }
    public void allTest(){
        Host rhost=new Host ();
        rhost.setHost("127.0.0.1");
        rhost.setPort(8081);
        rhost.setRootPath("/api");
        SysParam param =new SysParam ();
        param.setFormat("json");
        param.setAppKey("44b7afd8702f44869dec97f56aa3842d");
        String secret="6251fccc18b74ae1847c10b1c649ca73";
        param.setSecret(secret);
        TokenAction action=new TokenAction();
        SysParam paramget =new SysParam ();
        paramget.setFormat("json");
        paramget.setAppKey("44b7afd8702f44869dec97f56aa3842d");
        paramget.setSecret(secret);
        SysParam parampost =new SysParam ();
        parampost.setFormat("json");
        parampost.setAppKey("44b7afd8702f44869dec97f56aa3842d");
        parampost.setSecret(secret);
        try {
            Token token=action.singlelogin(rhost,param);
            paramget.setSessionId(token.getAccess_token());
            String getr=getTest(rhost,paramget);
            System.out.println(getr);
            parampost.setSessionId(token.getAccess_token());
            String postr=postTest(rhost,parampost);
            System.out.println(postr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void runTest(){
        Thread[]  threads=new Thread[5];
        for(int i=0;i<threads.length;i++){
            threads[i]=new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            while(true){
                                try {
                                    allTest();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                synchronized (this) {
                                    try {
                                        wait(200);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
            );
            threads[i].run();;

        }
    }
    public static void main(String[] args){
        Tester tester=new Tester();
        tester.runTest();


    }
}
