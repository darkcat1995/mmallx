package com.lichao.mmall.service;

import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.pojo.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */
public interface ICateoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse setCategoryName(String categoryName,Integer categoryId);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
