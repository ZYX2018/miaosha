package java0812.demo0812.miaosha.rabbitmq;

import java0812.demo0812.miaosha.catchException.GlobalException;
import java0812.demo0812.miaosha.config.MqConfig;
import java0812.demo0812.miaosha.miaoshaserver.MiaoshaOrderService;
import java0812.demo0812.miaosha.miaoshaserver.goodsService;
import java0812.demo0812.miaosha.miaoshaserver.miaoshaGooodsService;
import java0812.demo0812.miaosha.miaoshaserver.orderInfoService;
import java0812.demo0812.miaosha.servicevo.MiaoshaOrderVo;
import java0812.demo0812.miaosha.servicevo.goodsVo;
import java0812.demo0812.miaosha.servicevo.miaoshaMessage;
import java0812.demo0812.miaosha.sqlvo.miaoshaOrder;
import java0812.demo0812.service.OrderKey;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.vo.FailedMessage;
import java0812.demo0812.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;
/*
RabbitMq一共四种工作模式 指定一个队列一存一取是direct模式
 */
@Service
public class MqReceiver {

    @Autowired
    goodsService service;
    @Autowired
    MiaoshaOrderService mservice;
    @Autowired
    miaoshaGooodsService mgservice;
    @Autowired
    orderInfoService infoService;
    @Autowired
    RedisService redisService;


    private static Logger logger= LoggerFactory.getLogger(MqConfig.HeaderQUEUE);
//    @RabbitListener(queues = MqConfig.QUEUE)//监听指定队列
//    public void receiveMessage(String message){
//        logger.info("收到信息："+message);
//    }
//
//    @RabbitListener(queues = MqConfig.TOPICQUEU1)
//    public  void  receiveFromTopicQueue1(String message){
//        logger.info("1收到topic queue 的消息： "+message);
//    }
//
//    @RabbitListener(queues = MqConfig.TOPICQUEUE2)
//    public void receiveFormQueue2(String message){
//        logger.info("2收到来自 topic queue 的消息"+message);
//    }
    @RabbitListener(queues = MqConfig.HeaderQUEUE)
    public void  receiveFromHeaderQueue(byte[] bytes){
        logger.info("收到来自header 的消息："+new String(bytes));
    }

    @RabbitListener(queues = MqConfig.MiaoshaQueue)
    public void  miaoshareceiver(String message){
        logger.info("收到秒杀消息:"+message);
        miaoshaMessage miaoshaMessage=RedisService.stringToBean(message, java0812.demo0812.miaosha.servicevo.miaoshaMessage.class);
        //检查是否还有库存
        goodsVo goods=mgservice.getGoodsVoFoeMiaosha(miaoshaMessage.getGoodsId());
        if(goods==null){throw new GlobalException(FailedMessage.bindFailed("没有该商品"));}
        if(goods.getStockCount()<=0){
            redisService.setString(OrderKey.getOverFlagKey(),true);
            throw new GlobalException(FailedMessage.bindFailed("手速太慢，没商品已抢光"));
        }
        //检查是否是重复秒杀
        MiaoshaOrderVo vo=new MiaoshaOrderVo(miaoshaMessage.getUser().getId(),miaoshaMessage.getGoodsId());
        miaoshaOrder morder=mservice.getmiaoshaOrder(vo);
        if(morder!=null)throw  new GlobalException(FailedMessage.bindFailed("做人不能太贪心，您已经秒杀过该商品"));
        //创建秒杀订单
        miaoshaOrder newOrder=mservice.createOneMiaoshaOrder(goods,miaoshaMessage.getUser());
        if(newOrder==null)throw new GlobalException(FailedMessage.bindFailed("虽用尽全力但是被挤下来了"));
    }
}
