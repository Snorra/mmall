package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

import javax.servlet.http.HttpSession;

/**
 * Created by zj on 2019/3/25.
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;
    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password, HttpSession session) {
        ServerResponse response =  iUserService.login(username,password);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method= RequestMethod.POST)
    @ResponseBody
    public ServerResponse logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method=RequestMethod.POST)
    @ResponseBody
    public ServerResponse register(User user) {
        return iUserService.register(user);
    }

    @RequestMapping(value = "get_user_info.do", method=RequestMethod.POST)
    @ResponseBody
    public ServerResponse getUserInfo(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
    }

    @RequestMapping(value = "check_valid.do", method=RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkValid(String str,String type) {
        return iUserService.checkValid(str,type);
    }

    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }

    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse checkAnswer(String username,String password,String answer) {
        return iUserService.checkAnswer(username,password,answer);
    }

    @RequestMapping(value = "forget_reset_password.do")
    @ResponseBody
    public ServerResponse forgetResetPassword(String username,String passwordNew,String forgetToken) {
        return iUserService.forgotResetPassword(username, passwordNew, forgetToken);
    }

    @RequestMapping(value = "reset_password.do")
    @ResponseBody
    public ServerResponse resetPassword(String username,String passwordOld,String passwordNew,HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null) {
            return ServerResponse.createByErrorMessage("未登录");
        }
        return  iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    @RequestMapping(value = "update_infomation.do")
    @ResponseBody
    public ServerResponse update_infomation(HttpSession session,User user) {
        User currentuser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentuser == null) {
            return ServerResponse.createByErrorMessage("您未登录，请登录！");
        }
        user.setId(currentuser.getId());
        ServerResponse response = iUserService.updateInfomation(user);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "get_infomation.do")
    public ServerResponse get_infomation(HttpSession session) {
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"您需要登录");
        }
        return iUserService.getInfomation(currentUser.getId());
    }

}
