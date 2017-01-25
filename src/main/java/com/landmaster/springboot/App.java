package com.landmaster.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
//@Controller
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class App {
    @Value("spring.cloud.consul.host")
    private String consulHost;
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World my frist for spring cloud!("+consulHost+")";
    }

    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(App.class, args);
    }


}