package com.spring.test.xhyl;

/**
 * @ClassName: A
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/3
 * @Version: V1.0
 */
public class A {

    private B b;

    public void setB(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }

    public A() {
        System.out.println("A create SUCCESS");
    }
}
