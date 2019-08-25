package java0812.demo0812.miaosha.controllers;

import java0812.demo0812.miaosha.catchException.GlobalException;
import java0812.demo0812.miaosha.miaoshaserver.UserServer;
import java0812.demo0812.miaosha.sqlvo.Loginvo;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.miaosha.util.MD5Util;
import java0812.demo0812.miaosha.util.UUIDUtil;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.service.UserKey;
import java0812.demo0812.vo.FailedMessage;
import java0812.demo0812.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller

public class LoginController {

   final   static Logger log= LoggerFactory.getLogger(LoginController.class);
    public final static String CookiNameToken="token"; //token前缀
    public final static int CookieAndTokenLimitTime=3600*24;
    @Autowired
    MD5Util util;
    @Autowired
    UserServer userServer;
    @Autowired
    RedisService redisService;
    @RequestMapping("/login")
    public String loginView(){
        return "redirect:/login.htm";
    }
    /*
        登录验证
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public Result<String> dologin(HttpServletResponse response, @Valid Loginvo user){
       log.info(user.getId()+"尝试登录");
        if(user==null){
            return Result.getFailed(FailedMessage.INPUTNULLERROR);
        }else {
            miaoshaUser reuser=userServer.getOneByMobile(user.getId());//输入正常则检查该手机号用户是存在
            if(reuser==null) return Result.getFailed(FailedMessage.USERNOTEXIST);
            else {//用户存在则检查密码是否正确
                System.out.println(user.getPassword());
                String target= MD5Util.formPassToDbPass(user.getPassword(),reuser.getSalt());
                System.out.println(target);
                if(target.equals(reuser.getPassword())){
                    //登录成功 生成cookie标识用户
                    String token= UUIDUtil.uuid(); //创建token类似于cokie用于服务端辨识用户信息
                    //将token写入redis
                    System.out.println("newToken:"+token);
                    UserKey userKey=UserKey.userToken(token);
                    redisService.setString(userKey,reuser);
                    Cookie cookie=new Cookie(CookiNameToken,token);//创建cookie并写入客户端
                    cookie.setPath("/");
                    cookie.setMaxAge(userKey.limitTime());
                    response.addCookie(cookie);
                    return Result.getSuccess("success");
                    }else return Result.getFailed(FailedMessage.ERROPASSWD);
               }
           }
    }

    @RequestMapping("/userTest.do")
    @ResponseBody
    public Result<String> user(miaoshaUser user){
        return Result.getSuccess("a");
    }
}
