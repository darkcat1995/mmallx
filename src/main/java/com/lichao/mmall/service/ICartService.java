package com.lichao.mmall.service;

import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.vo.CartVo;

/**
 * Created by Administrator on 2017/7/19.
 */
public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);
    ServerResponse<CartVo> delete(Integer userId,String productIds);
    ServerResponse<CartVo> list(Integer userId);
    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);
    ServerResponse<Integer> getCartProductCount(Integer userId);
}
