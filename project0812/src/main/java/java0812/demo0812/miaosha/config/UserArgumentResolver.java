package java0812.demo0812.miaosha.config;

import com.alibaba.druid.util.StringUtils;
import java0812.demo0812.miaosha.controllers.LoginController;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    RedisService redisService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType()== miaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request=webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response=webRequest.getNativeResponse(HttpServletResponse.class);
        //获取前端传过来的token
        String paramToken=request.getParameter(LoginController.CookiNameToken);
        String CookieToken=getCookie(request,LoginController.CookiNameToken);
        if(StringUtils.isEmpty(paramToken)&&StringUtils.isEmpty(CookieToken))return null;
        String token= paramToken==null? CookieToken:paramToken;
       // System.out.println("token："+token);
        return redisService.checkUserAndFlushToken(response,token);//验证cookie是否过期并返回对应的用户对象
    }

    private String getCookie(HttpServletRequest request, String cookiNameToken) {
        Cookie[] cookies=request.getCookies();
        if(cookies==null||cookies.length<=0)return null;
        for (Cookie c:cookies){
            if(c.getName().equals(cookiNameToken))return c.getValue();
        }
        return null;
    }
}
