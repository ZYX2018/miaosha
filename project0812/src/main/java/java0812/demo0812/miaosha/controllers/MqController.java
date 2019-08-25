package java0812.demo0812.miaosha.controllers;

import java0812.demo0812.miaosha.rabbitmq.MqReceiver;
import java0812.demo0812.miaosha.rabbitmq.MqSender;
import java0812.demo0812.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MqController {


    @Autowired
    MqSender sender;

    @RequestMapping("/mqSend")
    @ResponseBody
    public Result sendMessage(){
        System.out.println("------------------------------");
       sender.sendHeaderMessage("hello rabbitmq headers ");
        return Result.getSuccess("send:"+"hello rabbitmq headers");
    }

}
