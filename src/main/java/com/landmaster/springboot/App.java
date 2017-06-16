package com.landmaster.springboot;

import com.landmaster.springboot.sdk.test.Tester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.Enumeration;

//@Controller
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
//@EnableDiscoveryClient
@RestController
public class App {

    @Autowired
    private Environment env;
    //@Value("${spring.cloud.consul.host}")
    private String consulHost;

   // @Value("${spring.cloud.consul.discovery.instanceId}")
    private String serviceId;

    @Value("${HOST_IP}")
    private String hostIP;
    @RequestMapping("/")
    @ResponseBody
    String home(@RequestHeader HttpHeaders headers, HttpServletRequest request, HttpServletResponse response) {
        Enumeration<String> names= request.getHeaderNames();
        String header="";
//        while(names.hasMoreElements()){
//            String k=names.nextElement();
//            header+="|"+k+"="+request.getHeader(k);
//        }
//       for(String name : names){
//
//       }
      Set keies=  headers.entrySet();
//
//
     for(Object o:keies){
                header+="|"+o;
      }
        return "Hello World my frist for spring cloud!("+consulHost+","+serviceId+","+hostIP+") "+header;

    }

    @RequestMapping("/ttt")
    @ResponseBody
    String ttt(@RequestHeader HttpHeaders headers, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("    exectue  tttttttttttttttttttttttt");
        Enumeration<String > ps=request.getParameterNames();
        while(ps.hasMoreElements()){
            String name=ps.nextElement();
            System.out.println("      the query name:"+name);
        }
        return "Hello World my frist for spring cloud!("+consulHost+","+serviceId+","+hostIP+")";

    }

    @RequestMapping("/myenv")
    public String env(@RequestParam("prop") String prop) {
        return env.getProperty(prop, "Not Found");
    }

    @RequestMapping(value = "/posttest",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public Object add(RequestParamTest entity) {
        return entity;

    }
    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(App.class, args);
        Tester tester=new Tester();
        Thread.sleep(10000);
        tester.runTest();
    }


}