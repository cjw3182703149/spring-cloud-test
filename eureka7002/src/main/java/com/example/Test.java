package com.example;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        ObjectOutputStream objectOutputStream = null;
        try {
            // 序列化
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("consumer//src//main//resources//1.txt")));
            objectOutputStream.writeObject(new String("我在这里"));//写入
            objectOutputStream.flush();//刷新缓存
            objectOutputStream.writeObject(new Person("我",20));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //反序列化
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("consumer//src//main//resources//1.txt"));
            try {
                Object object = objectInputStream.readObject();
                object = (String)object;
                System.out.println(object);
                Object object2 = objectInputStream.readObject();
                object2 = (Person)object2;
                System.out.println(object2.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Person implements Serializable{
    // 序列版本号,尽量显式的写出来
    public static final long serialVersionUID = 677987979L;
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
