package com.rabbitmq.service.impl;

import com.rabbitmq.bean.MsgLog;
import com.rabbitmq.mapper.MsgLogMapper;
import com.rabbitmq.service.MsgLogService;
import com.rabbitmq.util.JodaTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MsgLogServiceImpl
 * @Description: 消息记录日志业务
 * @author: yangtianzeng
 * @date: 2020/4/4 10:29
 */
public class MsgLogServiceImpl implements MsgLogService {
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public void insert(MsgLog msgLog) {
        msgLogMapper.insert(msgLog);
    }

    @Override
    public void updateStatus(String msgId, Integer status) {
        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setStatus(status);
        msgLog.setUpdateTime(new Date());
        msgLogMapper.updateStatus(msgLog);
    }

    @Override
    public MsgLog selectByMsgId(String msgId) {
        return msgLogMapper.selectByPrimaryKey(msgId);
    }

    @Override
    public List<MsgLog> selectTimeoutMsg() {
        return msgLogMapper.selectTimeoutMsg();
    }

    @Override
    public void updateTryCount(String msgId, Date tryTime) {
        // 一分钟后重新投递
        Date nextTryTime = JodaTimeUtil.plusMinutes(tryTime, 1);

        MsgLog msgLog = new MsgLog();
        msgLog.setMsgId(msgId);
        msgLog.setNextTryTime(nextTryTime);

        msgLogMapper.updateTryCount(msgLog);
    }
}
