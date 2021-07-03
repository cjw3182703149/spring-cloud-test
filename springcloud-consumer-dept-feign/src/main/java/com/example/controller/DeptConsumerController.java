package com.example.controller;

import com.example.pojo.Dept;
import com.example.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeptConsumerController {
    @Autowired
    private DeptClientService deptClientService;

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id) {
        return this.deptClientService.queryById(id);
    }

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept) {
        return this.deptClientService.add(dept);
    }
}
