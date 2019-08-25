package java0812.demo0812.miaosha.sqldao;

import com.mysql.jdbc.StringUtils;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
@Component
@Mapper
public interface UserMapper {

    public miaoshaUser getOneUser(@Param("mobile")String mobile,@Param("password")String passwd);
    public miaoshaUser getOneById(@Param("mobile")String mobile);
    @Insert("INSERT INTO miaoshauser (id,nickName,password,salt) VALUES(#{id},#{nickName},#{password},'wsad8246')")
    public void  insertOneUserTest(miaoshaUser user);
    public void  insertOneUser(miaoshaUser user);
    public void  updateOneUser(miaoshaUser user);
    @Update("UPDATE miaoshauser m SET m.`password`=#{id} WHERE m.id=#{password}")
    void updatePasswd(miaoshaUser user);
}
