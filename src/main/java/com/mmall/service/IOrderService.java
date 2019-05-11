package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;

import java.util.Map;

/**
 * Created by zj on 2019/4/6.
 */
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
    ServerResponse aliCallBack(Map<String,String> params);
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
    ServerResponse createOrder(Integer userId,Integer shippingId);
    ServerResponse cancel(Integer userId,Long orderNo);
    ServerResponse getOrderCartProduct(Integer userId);
    ServerResponse getOrderDetail(Integer userId,Long orderNo);
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);
    ServerResponse manageList(int pageNum,int pageSize);
    ServerResponse manageDetail(Long orderNo);
    ServerResponse manageSearch(Long orderNo,int pageNum,int pageSize);
    ServerResponse manageSendGoods(Long orderNo);
}
