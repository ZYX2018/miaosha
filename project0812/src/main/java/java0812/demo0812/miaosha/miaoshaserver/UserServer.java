package java0812.demo0812.miaosha.miaoshaserver;

import java0812.demo0812.miaosha.catchException.GlobalException;
import java0812.demo0812.miaosha.sqldao.UserMapper;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.miaosha.util.MD5Util;
import java0812.demo0812.miaosha.util.UUIDUtil;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.service.UserKey;
import java0812.demo0812.vo.FailedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

@Service
public class UserServer {
    @Autowired
    UserMapper mapper;

    @Transactional(readOnly = true)
    public miaoshaUser getOneUser(String mobie,String passwd){
        return mapper.getOneUser(mobie,passwd);
    }
    @Transactional
    public miaoshaUser getOneByMobile(String mobile){return mapper.getOneById(mobile);}
    @Transactional
    public void insertOneTest(miaoshaUser user){
       mapper.insertOneUserTest(user);
    }

    @Transactional
    public  void updateOne(miaoshaUser user){
        mapper.updateOneUser(user);
    }

    @Transactional
    public void updatePasswd(miaoshaUser user) {
        mapper.updatePasswd(user);
    }
}
