package com.spring.test.xhyl;

import java.lang.annotation.*;

/**
 * @ClassName: AutoWired
 * @Description: TODO 自定义AutoWired注解
 * @author: yangtz
 * @date: 2020/11/16
 * @Version: V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
@Documented
public @interface AutoWired1 {
}
