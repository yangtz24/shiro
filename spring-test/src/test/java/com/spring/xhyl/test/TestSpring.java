package com.spring.xhyl.test;

import com.spring.test.xhyl.A;
import com.spring.test.xhyl.AutoWired1;
import com.spring.test.xhyl.com.spring.test.AppApplication;
import com.spring.test.xhyl.controller.UserController;
import com.spring.test.xhyl.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

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
//        B b = context.getBean("b", B.class);
    }

    @Test
    public void test2() throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        // 创建对象
        UserService userService = new UserService();
        // 获取UserController中的属性
        Field serviceField = clazz.getDeclaredField("userService");
        serviceField.setAccessible(true);
        // 获取属性名称
        String serviceFieldName = serviceField.getName();
        // 构造set方法
        String setMethodName = "set" + serviceFieldName.substring(0, 1).toUpperCase() + serviceFieldName.substring(1, serviceFieldName.length());
        // 通过方法注入属性
        Method method = clazz.getMethod(setMethodName, UserService.class);
        // 获取对象
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());

    }

    @Test
    public void test3() {
        Class<UserController> clazz = UserController.class;
        // 获取所有属性值
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            // 获取属性注解
            AutoWired1 annotation = field.getAnnotation(AutoWired1.class);
            if (annotation != null) {
                field.setAccessible(true);
                // 获取属性类型
                Class<?> type = field.getType();
                Object o = null;
                try {
                    o = type.newInstance();
                    field.set(new UserController(), o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
