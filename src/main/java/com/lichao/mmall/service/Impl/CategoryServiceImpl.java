package com.lichao.mmall.service.Impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.dao.CategoryMapper;
import com.lichao.mmall.pojo.Category;
import com.lichao.mmall.service.ICateoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/18.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICateoryService {

    private Logger logger= LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName,Integer parentId){
        if (parentId==null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("添加商品参数错误");
        }
        Category category=new Category();
        category.setId(parentId);
        category.setName(categoryName);
        category.setStatus(true);
        int rowCount=categoryMapper.insert(category);
        if (rowCount>0){
            return  ServerResponse.createBySuccess("添加商品成功");
        }
        return ServerResponse.createByErrorMessage("添加商品失败");
    }

    public ServerResponse setCategoryName(String categoryName,Integer categoryId){
        if (categoryId==null || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorMessage("更新商品名称参数错误");
        }

        Category category=new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount=categoryMapper.updateByPrimaryKeySelective(category);

        if (rowCount>0){
            return ServerResponse.createBySuccess("更新商品名称成功");
        }
           return ServerResponse.createByErrorMessage("更新商品名称失败");

    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId){
        List<Category> categoryList=categoryMapper.selectCategoryByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前分类的子分类");
        }
         return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet= Sets.newHashSet();
        findChildCategory(categorySet,categoryId);//通过递归算法，填充好categorySet里的值
        List<Integer> categoryIdList= Lists.newArrayList();
        if (categoryId!=null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }


    //递归算法，查出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        Category category=categoryMapper.selectByPrimaryKey(categoryId);
        if (category!=null){
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有一个退出条件,我们的退出条件是查找他的子节点，如果为空则退出。
        List<Category> categoryList=categoryMapper.selectCategoryByParentId(categoryId);
        for (Category categoryItem:categoryList) {
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }


}
