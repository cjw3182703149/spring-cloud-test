package com.example.service;


import com.example.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

// 降级，就是如果你这个服务不想让别人使用，让他去调用其他的
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    public Object create(Throwable throwable) {
        return new DeptClientService() {
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id).setDname("不存在");
            }

            public List<Dept> queryAll() {
                return null;
            }

            public Boolean add(Dept dept) {
                return null;
            }
        };
    }
}
