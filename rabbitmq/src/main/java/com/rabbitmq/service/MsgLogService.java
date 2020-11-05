package com.rabbitmq.service;

import com.rabbitmq.bean.MsgLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: MsgLogService
 * @Description: 消息发送失败处理 日志记录
 * @author: yangtianzeng
 * @date: 2020/4/4 10:08
 */
@Service
@Transactional
public interface MsgLogService {

    /**
     * 插入消息日志
     *
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 更新消息状态
     *
     * @param msgId
     * @param status
     */
    void updateStatus(String msgId, Integer status);

    /**
     * 查询消息
     *
     * @param msgId
     * @return
     */
    MsgLog selectByMsgId(String msgId);

    /**
     * 查询超时消息
     *
     * @return
     */
    List<MsgLog> selectTimeoutMsg();

    /**
     * 更新重试次数
     *
     * @param msgId
     * @param tryTime
     */
    void updateTryCount(String msgId, Date tryTime);
}
