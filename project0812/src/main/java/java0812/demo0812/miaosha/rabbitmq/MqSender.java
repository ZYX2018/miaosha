package java0812.demo0812.miaosha.rabbitmq;

import java0812.demo0812.miaosha.config.MqConfig;
import java0812.demo0812.miaosha.servicevo.miaoshaMessage;
import java0812.demo0812.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqSender {
    private static Logger logger= LoggerFactory.getLogger(MqConfig.HeaderQUEUE);
    @Autowired
    AmqpTemplate amqpTemplate;

//    public void  sendMessage(Object message){ //向指定单个队列发送消息
//        String objectString= RedisService.beanToString(message);
//        amqpTemplate.convertAndSend(MqConfig.QUEUE,objectString);
//        logger.info("发送信息："+objectString);
//    }
//
//    //通过交换机向指定多个队列发送消息
//    public void sendTopicMessage(Object message){
//            String ObjectString=RedisService.beanToString(message);
//            amqpTemplate.convertAndSend(MqConfig.TOPICExchange,"topic.key1",ObjectString);
//    }
//
//    public void  sendTopicMessage2(Object message){
//        String objectString =RedisService.beanToString(message);
//        amqpTemplate.convertAndSend(MqConfig.TOPICExchange,"topic.key2",objectString);
//    }
////通过fanout广播消息
//public void  sendFanoutMessage(Object message){
//    String objectString =RedisService.beanToString(message);
//    amqpTemplate.convertAndSend(MqConfig.FANOUTEXCAHNGE,"",objectString);
//}

//通过header交换机发送消息
    public  void  sendHeaderMessage(Object message){
        String target=RedisService.beanToString(message);
        byte[] tmake=target.getBytes();
        MessageProperties properties=new MessageProperties();
        properties.setHeader("key1","value1");
        properties.setHeader("key2","value2");
        Message message1=new Message(tmake,properties);
        amqpTemplate.convertAndSend(MqConfig.HEADEREXCAHNGE,"",message1);
    }


    public void  miaoshaSender(miaoshaMessage message){
        String miaoshaMessage=RedisService.beanToString(message);
        amqpTemplate.convertAndSend(miaoshaMessage,MqConfig.MiaoshaQueue);
        logger.info("秒杀入队"+miaoshaMessage);
    }

}
