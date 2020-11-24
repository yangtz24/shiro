package com.sihai.wx.service;

import com.sihai.wx.entity.PayResult;
import com.sihai.wx.entity.PreOrderResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author leechenxiang
 * @version V1.0
 * @Title: OrderService.java
 * @Package com.sihai.wx.service
 * @Description: 处理微信支付的相关订单业务
 * Copyright: Copyright (c) 2016
 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 * @date 2017年8月31日 上午10:41:21
 */
public interface WxOrderService {

    /**
     * @param body
     * @param out_trade_no
     * @param total_fee
     * @return
     * @throws Exception
     * @Description: 调用微信接口进行统一下单
     * @author leechenxiang
     * @date 2017年8月31日 下午2:56:56
     */
    public PreOrderResult placeOrder(String body, String out_trade_no, String total_fee) throws Exception;

    /**
     * @param request
     * @return
     * @throws Exception
     * @Description: 获取支付结果
     * @author leechenxiang
     * @date 2017年9月1日 上午8:57:53
     */
    public PayResult getWxPayResult(HttpServletRequest request) throws Exception;

}
