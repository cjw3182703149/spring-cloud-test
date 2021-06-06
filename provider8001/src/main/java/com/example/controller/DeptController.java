package com.example.controller;

import com.example.pojo.Dept;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @PostMapping("/dept/add")
    @Transactional(rollbackFor = Exception.class)
    public boolean addDept(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable Long id) {
        return deptService.queryById(id);
    }

    @GetMapping("/dept/queryall")
    public List<Dept> queryAll() {
        return deptService.queryAll();
    }
}
