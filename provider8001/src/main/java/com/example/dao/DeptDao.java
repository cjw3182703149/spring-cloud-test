package com.example.dao;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeptDao {
    boolean addDept(Dept dept);

    Dept queryById(Long id);

    List<Dept> queryAll();
}
