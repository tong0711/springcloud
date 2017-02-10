package com.landmaster.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

//@Controller
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class App {

    @Autowired
    private Environment env;
    @Value("${spring.cloud.consul.host}")
    private String consulHost;

    @Value("${spring.cloud.consul.discovery.instanceId}")
    private String serviceId;

    @Value("${HOST_IP}")
    private String hostIP;
    @RequestMapping("/")
    @ResponseBody
    String home(   @RequestHeader HttpHeaders headers) {
      Set keies=  headers.entrySet();
      String header="";
      for(Object o:keies){
                header="|"+o;
      }
        return "Hello World my frist for spring cloud!("+consulHost+","+serviceId+","+hostIP+") "+header;

    }

    @RequestMapping("/ttt")
    @ResponseBody
    String ttt() {
        return "Hello World my frist for spring cloud!("+consulHost+","+serviceId+","+hostIP+")";

    }

    @RequestMapping("/myenv")
    public String env(@RequestParam("prop") String prop) {
        return env.getProperty(prop, "Not Found");
    }


    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(App.class, args);
    }


}