package java0812.demo0812.miaosha.miaoshaserver;

import java0812.demo0812.miaosha.catchException.GlobalException;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.service.UserKey;
import java0812.demo0812.vo.FailedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;


@Service
public class miaoshaUserService {
    @Autowired
    UserServer sqlUserService;
    @Autowired
    RedisService redisService;

    public miaoshaUser getUserById(String id){
        if(StringUtils.isEmpty(id))return null;
        //从redis缓存取ueser
        miaoshaUser user=redisService.getByKey(UserKey.getUserKeyById(id),miaoshaUser.class);
            if(user!=null)return user;
                //从mysql中查询user
                user=sqlUserService.getOneByMobile(id);
                if(user==null)throw new GlobalException(FailedMessage.USERNOTEXIST);
                //写入redis缓存
               try {
                   redisService.setString(UserKey.getUserKeyById(id),miaoshaUser.class);
               }catch (Exception e){
                   throw new GlobalException(FailedMessage.bindFailed("服务器在发呆"));
               }finally {
                   return user;
               }

    }

    public boolean updatePsswordByCheck(String userId,String oldPasswoed,String newPassword){
       return updatePassword(userId,oldPasswoed,newPassword,true);
    }

    public boolean updatePasswordPress(String userId,String newPassword){
       return updatePassword(userId,null,newPassword,false);
    }

    private boolean updatePassword(String userId,String oldPasswd,String newPasswd,boolean press){
        miaoshaUser user=getUserById(userId);
        if(press){ //是否验证旧密码
            if(!user.getPassword().equals(oldPasswd))throw new GlobalException(FailedMessage.bindFailed("旧密码错误"));
        }
       try {
           //更新sql数据库
           miaoshaUser tempUser=new miaoshaUser();
           tempUser.setPassword(newPasswd);
           tempUser.setId(userId);
           sqlUserService.updatePasswd(tempUser);
           //更新缓存user
           redisService.delteUser(UserKey.getUserKeyById(userId));
           //更新token
           user.setPassword(newPasswd);
           redisService.setString(UserKey.getUserKeyById(userId),miaoshaUser.class);
        return true;
       }catch (Exception e){
           throw new GlobalException(FailedMessage.bindFailed("更新失败请重试"));
       }
       }

    }
