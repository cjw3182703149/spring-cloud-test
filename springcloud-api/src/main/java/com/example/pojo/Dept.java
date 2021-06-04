package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) // 链式写法
public class Dept implements Serializable {  // 实体类，必须实体化
    private Long deptno;

    private String dname;

    private String dbSource;

    public Dept(String dname) {
        this.dname = dname;
    }
}
