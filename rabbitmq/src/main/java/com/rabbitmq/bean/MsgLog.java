package com.rabbitmq.bean;

import com.rabbitmq.common.Constant;
import com.rabbitmq.util.JodaTimeUtil;
import com.rabbitmq.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName: MsgLog
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MsgLog {

    private String msgId;
    private String msg;
    private String exchange;
    private String routingKey;
    private Integer status;
    private Integer tryCount;
    private Date nextTryTime;
    private Date createTime;
    private Date updateTime;

    public MsgLog(String msgId, Object msg, String exchange, String routingKey) {
        this.msgId = msgId;
        this.msg = JsonUtil.objToStr(msg);
        this.exchange = exchange;
        this.routingKey = routingKey;

        this.status = Constant.MsgLogStatus.DELIVERING;
        this.tryCount = 0;

        Date date = new Date();
        this.createTime = date;
        this.updateTime = date;
        this.nextTryTime = (JodaTimeUtil.plusMinutes(date, 1));
    }
}
