package com.rabbitmq;

import com.rabbitmq.consumber.Consumber;
import com.rabbitmq.producer.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private Consumber consumber;

    @Autowired
    private Producer producer;

    @Test
    public void test1() {
        String msg = "springboot rabbitMQ";
        producer.sendMsg(msg);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        String msg = "springboot rabbitMQ";
        producer.sendMsg(msg);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
