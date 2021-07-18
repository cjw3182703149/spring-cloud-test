package com.example.controller;

import com.example.pojo.Dept;
import com.example.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @HystrixCommand(fallbackMethod = "hQueryById")//失败了，就调用什么方法
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable Long id) {
        Dept dept = deptService.queryById(id);
        if (dept == null) {
            throw new RuntimeException("ID:"+id+"不存在");
        }
        return dept;
    }

    // 备选方案
    public Dept hQueryById(@PathVariable Long id) {
        return new Dept().setDeptno(id).setDname("id不存在");
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
