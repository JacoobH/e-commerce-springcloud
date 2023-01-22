package org.example.ecommerce.service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NacosClientService {
    @Autowired
    private DiscoveryClient discoveryClient;

    public List<ServiceInstance> getNacosClientInfo(String serviceId){
        log.info("request nacos client to get instance info : [{}]", serviceId);
        return discoveryClient.getInstances(serviceId);
    }
}
