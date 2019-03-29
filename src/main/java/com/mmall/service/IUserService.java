package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zj on 2019/3/25.
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);
    ServerResponse<User> register(User user);
    ServerResponse<User> checkValid(String str,String type);
    ServerResponse<User> selectQuestion(String username);
    ServerResponse checkAnswer(String username,String question,String answer);
    ServerResponse forgotResetPassword(String username,String passwordNew,String forgetToken);
    ServerResponse resetPassword(String passwordOld,String passwordNew,User user);
    ServerResponse updateInfomation(User user);
    ServerResponse getInfomation(int id);
    ServerResponse checkAdminRole(User user);
}