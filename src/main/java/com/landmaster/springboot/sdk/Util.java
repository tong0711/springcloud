package com.landmaster.springboot.sdk;

import com.landmaster.springboot.SecretUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tdl on 2017/6/15.
 */
public class Util {
    public static  String XML="xml";
    public static String JSON="json";
    public static  String SOAP="soap";
    public static String toQueryUril(String rootPath,SysParam param,Map<String,String> queryParam){
        //?method=wms_item_inf&format=xml&appKey=44b7afd8702f44869dec97f56aa3842d&v=1.0" +
        //"&sessionId="+sessionid+"&sign=" + sign+"&usercd=u&password=p&omsinfo=o"
        Map<String,String > allParam=new HashMap();
        if(queryParam!=null) {
            allParam.putAll(queryParam);
        }
        allParam.put("method", param.getMethod());
        allParam.put("v",param.getV());
        allParam.put("format",param.getFormat());
        allParam.put("appKey",param.getAppKey());
        allParam.put("sessionId",param.getSessionId());
        String    sign= SecretUtils.sign(allParam,param.getSecret());
        StringBuilder sbb=new StringBuilder();
        sbb.append(rootPath).append("?method=").
                append(param.getMethod()).append("&format=").
                append(param.getFormat()).append("&appKey=").
                append(param.getAppKey()).append("&v=").
                append(param.getV()).append("&sessionId=").
                append(param.getSessionId()).append("&sign=").append(sign);

        if(queryParam!=null) {
            Set<String> set=queryParam.keySet();
            for (String key : set) {
                sbb.append("&" + key + "=").append(queryParam.get(key));
            }
        }

        return sbb.toString();
    }
    public  static void main(String[] args){
        SysParam param=new SysParam ();
        param.setAppKey("234");
        param.setFormat("xml");
        param.setMethod("ttt");
        param.setSessionId("3333");
        param.setV("1.0");
        param.setSecret("secret");
        Map<String,String> queryParam=new HashMap();
        queryParam.put("u","u");
        String url=   Util.toQueryUril("/api",param,queryParam);
        System.out.println(url);
    }
}
