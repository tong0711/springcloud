package com.landmaster.springboot;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * Created by tdl on 2017/6/2.
 *     JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
 Client client = dcf.createClient("http://localhost:8080/soap/user?wsdl");
 Object[] objects = client.invoke("getUser", 10002L);
 //输出调用结果
 System.out.println(objects[0].getClass());
 System.out.println(objects[0].toString());
 */
//w
 @Configuration
public class CxfConfig {

    //public ServletRegistrationBean dispatcherServlet() {
        @Bean
        public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(), false,"/soap/*");
    }
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
        endpoint.publish("/user");
        return endpoint;
    }
}
