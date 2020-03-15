package com.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author HelloController
 * @description
 * @date 2019/8/6 14:24
 **/
@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RestTemplate restTemplate;
    //private Registration registration;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        //ServiceInstance instance = getServiceInstance();
        List<String> services = client.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = client.getInstances(service);
            for (ServiceInstance instance : instances) {
                logger.info("/hello, host:" + instance.getHost() + ", serviceId:" + instance.getServiceId());
            }
        }
        return "hello";
    }

    public String method1() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    /*public ServiceInstance getServiceInstance() {
        List<ServiceInstance> instances = client.getInstances(registration.getServiceId());
        if(instances != null && instances.size() > 0) {
            for (ServiceInstance instance : instances) {
                if(instance.getPort() == 8000) {
                    return instance;
                }
            }
        }
        return null;
    }*/
}
