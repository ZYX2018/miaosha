package java0812.demo0812.miaosha.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

@Configuration
public class MqConfig {

    public final static String HeaderQUEUE="HeaderQueue";
    public final static String MiaoshaQueue="Mqueue";
//    public final static String TOPICQUEU1="top1";
//    public final static String TOPICQUEUE2="top2";
//    public final static String TOPICExchange="topExchange";
//    public final static String FANOUTEXCAHNGE="fanoutExchange";
    public final static String HEADEREXCAHNGE="HeaderExchange";

    @Bean //创建消息队列
    public Queue getHealderQueue(){
        return new Queue(MiaoshaQueue,true);
    }




//
//    @Bean //创建消息队列
//    public Queue getQueue(){
//        return new Queue(QUEUE,true);
//    }
//    @Bean
//    public Queue getTop1Queue(){return new Queue(TOPICQUEU1,true);}
//    @Bean
//    public Queue getTop2Queue(){
//        return new Queue(TOPICQUEUE2,true);
//    }


//    @Bean//创建交topic换机
//    public TopicExchange getTopicExchange(){
//        return new TopicExchange(TOPICExchange);
//    }
//
//    @Bean
//    public FanoutExchange getFanoutExchange(){
//        return new FanoutExchange(FANOUTEXCAHNGE);
//    }



//    @Bean//将消息队列绑定到topic交换机  ，交换机将根据绑定的routingkey向队列分发消息
//    public Binding bindingToQueue1(){
//        return BindingBuilder.bind(getTop1Queue()).to(getTopicExchange()).with("topic.key1");
//    }
//    @Bean
//    public Binding bindingToQueue2(){
//        return BindingBuilder.bind(getTop2Queue()).to(getTopicExchange()).with("topic.#");
//    }
////将消息队列绑定至fanout交换机
//    @Bean
//    public  Binding bindingBuilder(){
//        return BindingBuilder.bind(getTop1Queue()).to(getFanoutExchange());
//    }
//
//    @Bean
//    public  Binding bindingBuilder2(){
//        return BindingBuilder.bind(getTop2Queue()).to(getFanoutExchange());
//    }
@Bean
public Queue getHeaderQueue(){
    return new Queue(HeaderQUEUE,true);
}

    //创建header交换机
    @Bean
    public  HeadersExchange getHeadersExchange(){
        return new HeadersExchange(HEADEREXCAHNGE);
    }
    //将队列绑定至header交换机
    @Bean
    public  Binding bingdingHeaderExchange(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("key1","value1");
        map.put("key2","value2");
        return BindingBuilder.bind(getHeaderQueue()).to(getHeadersExchange()).whereAll(map).match();
    }

}
