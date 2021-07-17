package com.example;

import java.io.*;
import java.lang.reflect.*;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, ClassNotFoundException, IOException {
        int[] a = new int[]{1,2,2,2,3,3,5,5,6,7,9,9};
        int[] b = new int[]{1,2,2,10,3,3,0,5,6,7,9,0};
        delete_all(a);
        for (int i=0;i<a.length-5;i++){
            System.out.print(a[i]+" ");
        }
    }
    public static boolean delete_all(int[] L) {
        if (L.length == 0)
            return false;
        int k=0;
        for (int i=1;i<L.length;i++) {
            if (L[i] == L[i-1])
                k++;
            else
                L[i-k] = L[i];
        }
        System.out.println(k);
        return true;
    }
}

interface Human {
    String getBelief();
    void eat(String food);
}
//被代理类
class SuperMan implements Human {

    public String getBelief() {
        return "super belief";
    }

    public void eat(String food) {
        System.out.println("eat"+food);
    }
}
//jdk的动态代理
class ProxyFactory {
    // 调用此方法，返回一个代理类的对象 object是被代理对象
    public static Object getProxyInstance(Object object) {
        // 类的加载器
        ClassLoader classLoader = object.getClass().getClassLoader();
        // 接口，因为需要是同一个接口
        Class<?>[] interfaces = object.getClass().getInterfaces();
        // 调用被代理类的同名方法
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        // 进行绑定
        myInvocationHandler.bind(object);
        return Proxy.newProxyInstance(classLoader,interfaces,myInvocationHandler);
    }
}
class MyInvocationHandler implements InvocationHandler {

    private Object object;//赋值时，需要使用把代理类的对象进行赋值
    public void bind(Object o) {
        this.object = o;
    }
    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法invoke
    //将被代理类要执行的方法a的功能就声明在invoke中
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用方法
        //object是被代理对象
        Object invoke = method.invoke(object, args);
        return invoke;
    }
}

