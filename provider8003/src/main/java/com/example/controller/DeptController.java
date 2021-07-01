package com.example.controller;

import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 获取一些配置的信息，就是具体的微服务
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    @Transactional(rollbackFor = Exception.class)
    public boolean addDept(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable Long id) {
        System.out.println("8003");
        return deptService.queryById(id);
    }

    @GetMapping("/dept/queryall")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }

    // 注册进来的微服务信息，团队合作使用，可以不使用这个
    @GetMapping("/dept/discovery")
    public Object discovery() {
        // 获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>"+services);
        //得到具体的微服务信息,通过具体的微服务id，applicationName
        List<ServiceInstance> provider = client.getInstances("PROVIDER");
        for (ServiceInstance serviceInstance : provider) {
            System.out.println(
                    serviceInstance.getHost() + "\t" +
                            serviceInstance.getPort()+"\t"+
                            serviceInstance.getUri() + "\t"+
                            serviceInstance.getServiceId()
            );
        }
        return this.client;
    }
}
