package serviceprovider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * restController = controller + responseBody
 * 1. 是一个controller
 * 2. 返回json
 */


@RestController
public class HelloWorldController {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello-worlds/{name}")
    public String getHelloWorld(@PathVariable String name){
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for (ServiceInstance serviceInstance : instances){
            logger.info(serviceInstance.getServiceId());
            logger.info(serviceInstance.getHost());
        }

        return "hello world" + name;
    }
}
