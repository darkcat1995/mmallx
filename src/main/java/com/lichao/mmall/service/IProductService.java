package com.lichao.mmall.service;

import com.github.pagehelper.PageInfo;
import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.pojo.Product;
import com.lichao.mmall.vo.ProductDetailVo;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String> setSaleStatus(Integer productId,Integer status);
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);
    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);
    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductBykeywordCategory(String keyword,Integer categoryId,int pageNum,int pageSize,String orderBy);
}
