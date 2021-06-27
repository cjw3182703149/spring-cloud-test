package com.example;

/**
 * 定义枚举类默认继承enum类
 */

@MyAnnotation("hello")
public class Test {
    public static void main(String[] args) {

    }
}

// 自定义注解
@interface MyAnnotation {
    String value() default "hello";
}