/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:VolatileVisibility.java
 * @Prject: com.rabbitmq.volatiles
 * @Package: com.rabbitmq.volatiles
 * @author: yangtianzeng
 * @date: 2020/1/21 9:48
 * @version: V1.0
 */
package com.rabbitmq.volatiles;

/**
 * @ClassName: VolatileVisibility
 * @Description: volatile 关键字
 * @author: yangtianzeng
 * @date: 2020/1/21 9:48
 */
public class VolatileVisibility {

    public static class TestData {
        volatile int num = 0;

        public void updateNum() {
            this.num = 1;
        }
    }

    public static void main(String[] args) {
        final TestData testData = new TestData();

        new Thread(() -> {
            System.out.println(testData.num + " A");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testData.updateNum();
            System.out.println(testData.num + " B");
        }).start();

        while (testData.num == 0) {

        }

        System.out.println(testData.num + " C");
    }
}
