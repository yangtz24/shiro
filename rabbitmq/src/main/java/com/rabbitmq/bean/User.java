package com.rabbitmq.bean;

import java.io.Serializable;

/**
 * @author
 * @description User
 * @date 2019/11/29 10:18
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 5559910050731211768L;

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
