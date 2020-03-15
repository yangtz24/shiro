package com.learn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 * @description Test
 * @date 2019/9/5 15:00
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test implements Serializable {
    private static final long serialVersionUID = -3899203627930881779L;

    private String name;

    private Integer age;
}
