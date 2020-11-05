package com.spring.xhyl.test;

import com.spring.test.xhyl.A;
import com.spring.test.xhyl.B;
import com.spring.test.xhyl.com.spring.test.AppApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestSpring
 * @Description: TODO
 * @author: yangtz
 * @date: 2020/11/3
 * @Version: V1.0
 */
@SpringBootTest(classes = AppApplication.class)
@RunWith(SpringRunner.class)
public class TestSpring {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
    }
}
