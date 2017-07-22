package com.lichao.mmall.controller.portal;

import com.lichao.mmall.common.Const;
import com.lichao.mmall.common.ResponseCode;
import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.pojo.User;
import com.lichao.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/7/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
       ServerResponse<User> response= iUserService.login(username,password);
        //
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);   //登出功能，将session的key值删除即可
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user){
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }

    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
      User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user!=null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录无法获取当前用户的信息");
    }

    @RequestMapping(value = "forget_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);

    }

    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String anwser){
        return iUserService.checkAnswer(username,question,anwser);
    }

    @RequestMapping(value = "forget_rest_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetRestPassword(username,passwordNew,forgetToken);
    }

    @RequestMapping(value = "rest_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> restPassword(HttpSession session,String passwordOld,String passwordNew){
       User user=(User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.restPassword(passwordOld,passwordNew,user);
        }

    @RequestMapping(value = "update_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInfo(HttpSession session,User user){
        User currentUser=(User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser==null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
           user.setId(currentUser.getId()); //为了防止越权的问题，我们从session中获取登陆用户的userId
           user.setUsername(currentUser.getUsername()); //username不能被更新，直接从session中获取username ，username和userid都是从登陆用户中获取
           ServerResponse<User> response=iUserService.updateInfo(user);
           if (response.isSuccess()){
               session.setAttribute(Const.CURRENT_USER,response.getData());
           }
           return response;
    }

    @RequestMapping(value = "get_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInfo(HttpSession session){
        User currentUser=(User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser==null){
            return ServerResponse.createByErrorCodeMessage("未登录，需要强制登录，status=10",ResponseCode.NEED_LOGIN.getCode());
        }
        return iUserService.getInfo(currentUser.getId());
    }

    }

