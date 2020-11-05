package com.spring.test.xhyl;

/**
 * @ClassName: B
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/3
 * @Version: V1.0
 */
public class B {

    private A a;

    public void setA(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    public B() {
        System.out.println("B create SUCCESS");
    }
}
