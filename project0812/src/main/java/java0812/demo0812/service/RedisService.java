package java0812.demo0812.service;

import com.alibaba.fastjson.JSON;
import java0812.demo0812.miaosha.config.RpConfig;
import java0812.demo0812.miaosha.controllers.LoginController;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class RedisService {
    @Autowired
    RpConfig config;


    @Autowired
    JedisPool pool;
//Sting
    public <T> T getByKey(KeyPrefix prefix,Class<T> tClass){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            String temp=jedis.get(prefix.getKeyPrefix());
            if(temp==null)return null;
            T t=stringToBean(temp,tClass);
            return t;
        }finally {
            jedis.close();
        }
    }

    public <T> List<T> getList(KeyPrefix keyName,Class<T> tClass){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
            Long len=jedis.llen(keyName.getKeyPrefix());
            if(len==0)return null;
            List<T> list=new ArrayList<T>(len.intValue());
            for (int i = 0; i <len; i++) {
                T temp=stringToBean(jedis.lindex(keyName.getKeyPrefix(),i),tClass);
                list.add(temp);
            }
            return list;
        }finally {
            jedis.close();
        }

    }
    public <T> Boolean containKey(KeyPrefix keyPrefix ){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            return jedis.exists(keyPrefix.getKeyPrefix());
        }finally {
            jedis.close();
        }

    }

    public void delteUser(UserKey userKeyById) {
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            jedis.del(userKeyById.getKeyPrefix());
        }finally {
            jedis.close();
        }
    }

    public void setKeyLimitTime(KeyPrefix key,int limit){
        if(key!=null){
            Jedis jedis=null;
            try {
                jedis=pool.getResource();
                jedis.expire(key.getKeyPrefix(),limit);
            }finally {
                jedis.close();
            }
        }
    }

    public <T> long incrKey(KeyPrefix keyPrefix,long num ){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            return jedis.incrBy(keyPrefix.getKeyPrefix(),num);
        }finally {
            jedis.close();
        }

    }

    public <T> long decrKey(KeyPrefix keyPrefix,long num ){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            return jedis.decrBy(keyPrefix.getKeyPrefix(),num);
        }finally {
            jedis.close();
        }

    }

    public <T> Boolean  setString(KeyPrefix key,T value ){
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            //System.out.println(value);
            String str=beanToString(value);
           // System.out.println(str);
            if(str==null){
                return false;
            }
            if(key.limitTime()>0){
                jedis.setex(key.getKeyPrefix(),key.limitTime(),str);

            }else jedis.set(key.getKeyPrefix(),str);
            return true;
        }finally {
            jedis.close();
        }
     
    }

    public <T> boolean setList(KeyPrefix keyName,List<T> list) {
        Jedis jedis=null;
        try {
            jedis=pool.getResource();
            if(jedis.exists(keyName.getKeyPrefix()))return true;
            for(T t:list){
                String objString=beanToString(t);
                if(objString==null)return false;
                jedis.rpush(keyName.getKeyPrefix(),objString);
            }
            if(keyName.limitTime()>0) jedis.expire(keyName.getKeyPrefix(),keyName.limitTime());
            return true;
        }finally {
           jedis.close();
        }

    }

    public static  <T> String beanToString(T value) {
        if(value==null){
            return null;
        }
        Class<?> classz=value.getClass();
        if(classz==String.class){
            return (String)value;
        }else if(classz==int.class||classz==Integer.class||classz==long.class||classz==Long.class){
            return ""+value;
        }else if(classz==float.class||classz==Float.class||classz==Double.class||classz==double.class){
            return ""+value;
        } else {
                return JSON.toJSONString(value);
            }
        }

    public static  <T> T stringToBean(String temp,Class<T> tClass) {

        if(temp==null||temp.length()<=0){
            return null;
        }
        if(tClass==String.class){
            return (T) temp;
        }else if(tClass==int.class||tClass==Integer.class){
            return (T) Integer.valueOf(temp);
        }else if(tClass==float.class||tClass==Float.class){
            return (T) Float.valueOf(temp);
        }else if(tClass==Double.class||tClass==double.class) {
            return (T) Double.valueOf(temp);
        } else {
            return JSON.toJavaObject(JSON.parseObject(temp),tClass);
        }
    }

    public  miaoshaUser checkUserAndFlushToken(HttpServletResponse response, String token){
        if(token==null)return null;
        miaoshaUser user=getByKey(UserKey.userToken(token),miaoshaUser.class);
       // System.out.println("Redisuser: "+user);
        if(user!=null){
            UserKey userKey=UserKey.userToken(token);
            setKeyLimitTime(userKey, LoginController.CookieAndTokenLimitTime);//更新token有效时间
            Cookie cookie=new Cookie(LoginController.CookiNameToken,token);
            cookie.setMaxAge(LoginController.CookieAndTokenLimitTime);
            cookie.setPath("/");
            response.addCookie(cookie);//刷新客户端的cookie
        }
        return user;
    }

//---redi s factory
       @Bean
public JedisPool factory(){
                JedisPoolConfig poolConfig=new JedisPoolConfig();
                poolConfig.setMaxTotal(config.getMaxActive());
                poolConfig.setMaxIdle(config.getMaxIdle());
                poolConfig.setMaxWaitMillis(config.getMaxWait());
                poolConfig.setTestOnBorrow(true);
               return  new JedisPool(poolConfig,config.getHost(),config.getPort(),config.getTimeout()*1000,config.getPassword(),0);
    }


}
