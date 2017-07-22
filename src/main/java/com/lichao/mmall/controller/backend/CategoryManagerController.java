package com.lichao.mmall.controller.backend;

import com.lichao.mmall.common.Const;
import com.lichao.mmall.common.ResponseCode;
import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.pojo.User;
import com.lichao.mmall.service.ICateoryService;
import com.lichao.mmall.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.SavepointManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/7/18.
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManagerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICateoryService iCateoryService;

    @RequestMapping("add.category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage("用户需要登录",ResponseCode.NEED_LOGIN.getCode());
        }
        if (iUserService.checkRole(user).isSuccess()){
            return iCateoryService.addCategory(categoryName,parentId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权限操作");
        }
    }

    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session,String categoryName,Integer categoryId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage("用户需要登录",ResponseCode.NEED_LOGIN.getCode());
        }
        if (iUserService.checkRole(user).isSuccess()){
            return iCateoryService.setCategoryName(categoryName,categoryId);

        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权限操作");
        }
    }

    @RequestMapping("get_category.do")
    @ResponseBody
    //获取子节点，并且是平级的category信息
    public ServerResponse getChildrenParallelCategory(HttpSession session,@RequestParam(value ="categoryId",defaultValue = "0") Integer categoryId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage("用户需要登录",ResponseCode.NEED_LOGIN.getCode());
        }
        if (iUserService.checkRole(user).isSuccess()){
            //查询子节点的category信息，并且不递归，保持平级
            return iCateoryService.getChildrenParallelCategory(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权限操作");
        }
    }

    @RequestMapping("get_category_id.do")
    @ResponseBody
    //获取子节点，并且是平级的category信息
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value ="categoryId",defaultValue = "0") Integer categoryId){
        User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage("用户需要登录",ResponseCode.NEED_LOGIN.getCode());
        }
        if (iUserService.checkRole(user).isSuccess()){
           //查询当前节点的id和递归子节点的id
            return iCateoryService.selectCategoryAndChildrenById(categoryId);
        }else {
            return ServerResponse.createByErrorMessage("不是管理员，无权限操作");
        }
    }
}
