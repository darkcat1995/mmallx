package com.lichao.mmall.service;

import com.lichao.mmall.common.ServerResponse;
import com.lichao.mmall.pojo.User;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkValid(String str,String type);
    ServerResponse selectQuestion(String username);
    ServerResponse<String> checkAnswer(String username,String password,String answer);
    ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken);
    ServerResponse<String> restPassword(String passwordOld,String passwordNew,User user);
    ServerResponse<User> updateInfo(User user);
    ServerResponse<User> getInfo(Integer userId);
    ServerResponse checkRole(User user);
}
