package java0812.demo0812;

import java0812.demo0812.miaosha.miaoshaserver.MiaoshaOrderService;
import java0812.demo0812.miaosha.miaoshaserver.UserServer;

import java0812.demo0812.miaosha.miaoshaserver.goodsService;
import java0812.demo0812.miaosha.miaoshaserver.miaoshaGooodsService;
import java0812.demo0812.miaosha.servicevo.MiaoshaOrderVo;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.miaosha.util.MD5Util;
import java0812.demo0812.miaosha.util.UUIDUtil;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.service.UserKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo0812ApplicationTests {

    @Autowired
    MD5Util util;
    @Autowired
    UserServer userServer;
    @Autowired
    RedisService redisService;
    @Autowired
    goodsService gService;
    @Autowired
    MiaoshaOrderService moservice;
    @Autowired
    miaoshaGooodsService mgservice;

    private static  String salt="1a2b3c4d";
    private  static String start="13359718053";
        @Test
    public  void contextLoads() throws IOException {
//        BufferedWriter writer=new BufferedWriter(new FileWriter("C:/Users/ROOT/Desktop/dataConfig.txt"));
//        BigInteger integer=new BigInteger(start);
//        for(int i=1;i<=5000;i++){
//            BigInteger add=new BigInteger(Integer.toString(i));
//            String id=integer.add(add).toString();
            //朝着sql数据库写miaosha用户
//            miaoshaUser tempUser=new miaoshaUser();
//            tempUser.setId(id);
//            tempUser.setPassword("root");
//            tempUser.setNickName(id);
//            tempUser.setAddress("黑龙江省哈尔滨市");
//            tempUser.setHead("@@");
//            userServer.insertOneTest(tempUser);
          //  朝着redis写token
        //    String token= UUIDUtil.uuid(); //创建token类似于cokie用于服务端辨识用户信息
         //   将token写入redis
//            System.out.println(i);
//            UserKey userKey=UserKey.userToken(token);
//            redisService.setString(userKey,tempUser);
//            将和用户token写入txt
//            writer.write(id+","+token);
//            if(i==5000)break;
//            writer.newLine();
//        }
//    }

//    @Test
//    public void method(){
//        System.out.println(mgservice.getAllCount().toString());
  }
}
