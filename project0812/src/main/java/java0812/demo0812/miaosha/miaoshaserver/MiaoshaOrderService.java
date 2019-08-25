package java0812.demo0812.miaosha.miaoshaserver;

import java0812.demo0812.miaosha.catchException.GlobalException;
import java0812.demo0812.miaosha.servicevo.MiaoshaOrderVo;
import java0812.demo0812.miaosha.servicevo.goodsVo;
import java0812.demo0812.miaosha.sqldao.miaoshaOrderDao;
import java0812.demo0812.miaosha.sqlvo.miaoShaGoods;
import java0812.demo0812.miaosha.sqlvo.miaoshaOrder;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.miaosha.sqlvo.orderInfo;
import java0812.demo0812.service.OrderKey;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.vo.FailedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class  MiaoshaOrderService {
     @Autowired
     MiaoshaOrderService mservice;
       @Autowired
       miaoshaOrderDao dao;
       @Autowired
       miaoshaGooodsService mgservice;
       @Autowired
       goodsService gservice;
       @Autowired
       orderInfoService oInservice;
       @Autowired
        RedisService redisService;
//查询秒杀订单
       @Transactional(readOnly = true)
       public miaoshaOrder getmiaoshaOrder(MiaoshaOrderVo vo){
            //先从缓存取
            miaoshaOrder order=redisService.getByKey(OrderKey.getmiaoshaOrderKeyId(vo.getUserId()+vo.getGoodsId()),miaoshaOrder.class);
            if(order!=null)return order;
            //缓存没有则从mysql中取
            order=dao.selectOneMiaoshaOrder(vo);
            if(order==null)return null;
            redisService.setString(OrderKey.getmiaoshaOrderKeyId(vo.getUserId()+vo.getGoodsId()),order);
            return order;
       }

       @Transactional
       public miaoshaOrder createOneMiaoshaOrder(goodsVo goods, miaoshaUser user){
              //秒杀开始
                try {
                    //减少库存
                  boolean flag= mgservice.updateCount(goods.getGoodsId());
                  if(!flag)return null;
                    //创建秒杀订单
                    orderInfo info=new orderInfo(user.getId(),goods.getGoodsId(),user.getAddress(), goods.getGoodsName(), 1, goods.getMiaoshaPrice(), 6, 0, new Date(System.currentTimeMillis()),null);
                    oInservice.insertOne(info);
                    miaoshaOrder order=new miaoshaOrder();
                    order.setGoodsId(goods.getGoodsId());
                    order.setUserId(user.getId());
                    order.setOrderId(info.getId());
                    order.setInfo(info);
                    dao.insertOneMiaoshaOrder(order);
                    redisService.setString(OrderKey.getmiaoshaOrderKeyId(order.getUserId()+order.getGoodsId()),order);
                    return order;
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
              }

              public void  setOverSell(){
                  redisService.setString(OrderKey.getOverFlagKey(),true);
              }

              public boolean getOverSell(){
                    return redisService.getByKey(OrderKey.getOverFlagKey(),boolean.class);
              }

}
