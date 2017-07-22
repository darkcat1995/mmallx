package com.lichao.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.service.IProductService;
import com.lichao.mmall.vo.ProductDetailVo;
import com.lichao.mmall.vo.ProductListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/19.
 */
@Controller
@RequestMapping("/product/")
public class PoductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "10")String orderBy){

        return iProductService.getProductBykeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);

    }


}
