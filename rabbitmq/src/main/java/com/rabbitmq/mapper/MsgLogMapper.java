package com.rabbitmq.mapper;

import com.rabbitmq.bean.MsgLog;

import java.util.List;

/**
 * @ClassName: MsgLogMapper
 * @Description:
 * @author: yangtianzeng
 * @date: 2020/4/4 10:24
 */
public interface MsgLogMapper {

    /**
     * 添加
     * @param msgLog
     */
    void insert(MsgLog msgLog);

    /**
     * 修改状态
     * @param msgLog
     */
    void updateStatus(MsgLog msgLog);

    /**
     * 查询超时消息
     * @return
     */
    List<MsgLog> selectTimeoutMsg();

    /**
     * 更新重试次数
     * @param msgLog
     */
    void updateTryCount(MsgLog msgLog);

    /**
     * 根据主键查询
     * @param msgId
     * @return
     */
    MsgLog selectByPrimaryKey(String msgId);
}
