package com.rabbitmq.common;

/**
 * @ClassName: Constant
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 10:13
 */
public class Constant {

    public interface MsgLogStatus {
        Integer DELIVERING = 0;// 消息投递中
        Integer DELIVER_SUCCESS = 1;// 投递成功
        Integer DELIVER_FAIL = 2;// 投递失败
        Integer CONSUMED_SUCCESS = 3;// 已消费
    }
}
