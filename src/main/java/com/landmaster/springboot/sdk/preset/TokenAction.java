package com.landmaster.springboot.sdk.preset;

import com.landmaster.springboot.sdk.Host;
import com.landmaster.springboot.sdk.MyHttpPost;
import com.landmaster.springboot.sdk.SysParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tdl on 2017/6/16.
 */
public class TokenAction {
    //{"access_token":"d36d02f769962d22ee8194e6d72ad3b5","token_type":"Bearer","expires_in":43199}
    public Token singlelogin(Host host,SysParam param) throws IOException {
        MyHttpPost post=new MyHttpPost();
        param.setMethod("singlelogin");
        param.setV("1.0");
        param.setFormat("json");
        //http://localhost:8081/api?method=singlelogin&appKey=44b7afd8702f44869dec97f56aa3842d&v=1.0&
        // client_id=44b7afd8702f44869dec97f56aa3842d
        // &client_secret=6251fccc18b74ae1847c10b1c649ca73&grant_type=client_credentials&scope=read
        Map<String,String> qm=new HashMap();
        qm.put("client_id",param.getAppKey());
        qm.put("client_secret",param.getSecret());
        qm.put("grant_type","client_credentials");
        qm.put("scope","read");
        String result=post.execute(host,param,qm,"","application/x-www-form-urlencoded");
        result=result.replaceAll("[{}]","");
        String[] datas=result.split(",");
        System.out.println("----------:"+result);
        Token token=new Token();
        String re="\"";
        token.setAccess_token(datas[0].split(":")[1].replaceAll(re,""));
        token.setToken_type(datas[1].split(":")[1].replaceAll(re,""));
        token.setExpires_in(datas[2].split(":")[1]);
        return token;
    }
    public static void main(String[] args) throws IOException {
        TokenAction action =new TokenAction();
        Host rhost=new Host ();
        rhost.setHost("127.0.0.1");
        rhost.setPort(8081);
        rhost.setRootPath("/api");
        SysParam param =new SysParam ();
        param.setV("1.0");
        //param.setSessionId("d36d02f769962d22ee8194e6d72ad3b5");
        param.setMethod("posttest");
        param.setFormat("json");
        param.setAppKey("44b7afd8702f44869dec97f56aa3842d");
        String secret="6251fccc18b74ae1847c10b1c649ca73";
        param.setSecret(secret);
        Token token=action.singlelogin(rhost,param);
        System.out.println(token);
    }
}
