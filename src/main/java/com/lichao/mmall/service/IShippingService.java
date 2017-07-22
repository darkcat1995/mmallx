package com.lichao.mmall.service;

import com.github.pagehelper.PageInfo;
import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.pojo.Shipping;

/**
 * Created by Administrator on 2017/7/19.
 */
public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse del(Integer usrId,Integer shippingId);
    ServerResponse update(Integer userId, Shipping shipping);
    ServerResponse<Shipping> select(Integer usrId,Integer shippingId);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
