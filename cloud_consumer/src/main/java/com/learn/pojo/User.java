package com.learn.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author User
 * @description
 * @date 2019/8/7 13:54
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 2661584271661092416L;

    private Integer id;

    private String name;

    private Integer age;
}
