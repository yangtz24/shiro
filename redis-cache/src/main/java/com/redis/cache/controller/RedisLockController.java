package com.redis.cache.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName: RedisLockController
 * @Description: TODO  redis分布式锁
 * @author: yangtz
 * @date: 2020/11/4
 * @Version: V1.0
 */
@RestController
public class RedisLockController {

    private static final String REDIS_LOCK = "georgeBoy";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;


    @GetMapping("buy_goods")
    public String buyGoods() throws Exception {

        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();


        RLock rLock = redisson.getLock(REDIS_LOCK);
        rLock.lock();
        try {

            // 加锁
            // setNx(k, v)
//            boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);

//            if (!flag) {
//                return "抢锁失败";
//            }

            String result = stringRedisTemplate.opsForValue().get("test01");
            int number = result == null ? 0 : Integer.valueOf(result);

            if (number <= 0) {
                return "库存不足";
            }

            int realNumber = number - 1;
            stringRedisTemplate.opsForValue().set("test01", String.valueOf(realNumber));
            return "成功买到" + "库存还有" + realNumber + "个";

        } finally {
            // redisson
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                rLock.unlock();
            }

            // 判断是否是自己的
//            if (value.equals(stringRedisTemplate.opsForValue().get(REDIS_LOCK))) {
//                stringRedisTemplate.delete(REDIS_LOCK);
//            }

            // redis事务
            /*while (true) {
                // 开启redis 事务监控
                stringRedisTemplate.watch(REDIS_LOCK);
                if (stringRedisTemplate.opsForValue().get(REDIS_LOCK).equals(value)) {
                    // 开启支持事务
                    stringRedisTemplate.setEnableTransactionSupport(true);
                    // 开启事务
                    stringRedisTemplate.multi();
                    // 删除锁
                    stringRedisTemplate.delete(REDIS_LOCK);
                    // 执行事务
                    List<Object> list = stringRedisTemplate.exec();
                    if (list == null) {
                        continue;
                    }
                }
                stringRedisTemplate.unwatch();
                break;
            }*/

            // lua脚本
//            JedisPool jedisPool = new JedisPool("123.56.143.183", 6379);
//            Jedis jedis = jedisPool.getResource();
//
//            String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
//                    "then\n" +
//                    "    return redis.call(\"del\",KEYS[1])\n" +
//                    "else\n" +
//                    "    return 0\n" +
//                    "end";
//
//           try {
//               Object obj = jedis.eval(script, Collections.singletonList(REDIS_LOCK), Collections.singletonList(value));
//
//               if ("1".equals(obj.toString())) {
//                   System.out.println("删除成功");
//               } else {
//                   System.out.println("删除失败");
//               }
//           } finally {
//               if (jedis != null) {
//
//                   jedis.close();
//               }
//
//           }




        }
    }
}
