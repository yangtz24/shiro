package com.ytz.snowflake.controller;

import com.ytz.snowflake.component.IdGeneratorSnowflake;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: SnowFlakeController
 * @Description: TODO
 * @author: basketBoy
 * @date: 2020/6/23
 * @Version: V1.0
 */
@RestController
@RequestMapping("rest/snowflake")
public class SnowFlakeController {

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * 测试生成雪花算法
     * @return
     */
    @GetMapping("")
    public String snowflake() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            threadPool.submit(() -> {
                System.out.println(idGeneratorSnowflake.snowflakeId());
            });
        }
        threadPool.shutdown();
        return "SUCCESS";
    }
}
