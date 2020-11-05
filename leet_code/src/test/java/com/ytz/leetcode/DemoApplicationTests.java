package com.ytz.leetcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
//        IntStream.range(1, 10)
//                .peek(x -> System.out.print("\nA" + x))
//                .limit(3)
//                .peek(x -> System.out.print("B" + x))
//                .forEach(x -> System.out.print("C" + x));
        IntStream.range(1, 10)
                .peek(x -> System.out.print("\nA" + x))
                .skip(6)
                .peek(x -> System.out.print("B" + x))
                .forEach(x -> System.out.print("C" + x));
    }

}
