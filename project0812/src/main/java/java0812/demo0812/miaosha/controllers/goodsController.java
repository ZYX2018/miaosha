package java0812.demo0812.miaosha.controllers;

import java0812.demo0812.miaosha.catchException.GlobalException;
import java0812.demo0812.miaosha.miaoshaserver.MiaoshaOrderService;
import java0812.demo0812.miaosha.miaoshaserver.goodsService;
import java0812.demo0812.miaosha.miaoshaserver.miaoshaGooodsService;
import java0812.demo0812.miaosha.miaoshaserver.orderInfoService;
import java0812.demo0812.miaosha.rabbitmq.MqSender;
import java0812.demo0812.miaosha.servicevo.*;
import java0812.demo0812.miaosha.sqlvo.miaoshaOrder;
import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.miaosha.sqlvo.orderInfo;
import java0812.demo0812.service.GoodsKey;
import java0812.demo0812.service.MiaoshaGoodsKey;
import java0812.demo0812.service.RedisService;
import java0812.demo0812.service.pageKey;
import java0812.demo0812.vo.FailedMessage;
import java0812.demo0812.vo.Result;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class goodsController implements InitializingBean  {
    Map<String,Boolean> goodsMap=new HashMap<>();
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
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    MqSender mqSender;

    @RequestMapping(value = "/glist" )
    @ResponseBody
    public Result turnToGoodsList( miaoshaUser user){
        if(user==null)throw  new GlobalException(FailedMessage.bindFailed("未登陆"));
        List<goodsVo> list=new ArrayList<>();
        list=redisService.getList(GoodsKey.getgoodsListKey("glist"),goodsVo.class);
        if(list==null)throw  new GlobalException(FailedMessage.bindFailed("没有商品"));
        System.out.println(list.toString());
        return Result.getSuccess(list);
    }

@RequestMapping("/dogoodsDetail.do")  //商品详情
@ResponseBody
    public Result<goodsDetialVo> SendValToGoodsDetile(String goodsId,miaoshaUser user){
        if(user==null)return Result.getFailed(FailedMessage.UNlOGIN);
        if(StringUtils.isEmpty(goodsId))return  Result.getFailed(FailedMessage.bindFailed("页面错误"));
        try {
            System.out.println(goodsId);
            //获取页面显示数据
            goodsDetialVo vo=new goodsDetialVo();
            vo.setMsUser(user);
            goodsVo g= service.getGoodsById(goodsId);
            vo.setGvo(g);
            long  begin=g.getStartDate().getTime();//秒杀开始时间
            long end=g.getEndDate().getTime();//秒杀结束时间
            long now= System.currentTimeMillis();//当前时间
            // System.out.println("now："+new Date(now)+"begin:"+new Date(begin));
            int miaoshaStatus=0;
            if(now<begin){
                miaoshaStatus=0;
            }else  if(now>end){
                miaoshaStatus=2;
            }else {
                miaoshaStatus=1;
            }
            vo.setMiaoshaStatus(miaoshaStatus);vo.setRemainSeconds((begin-now)/1000);
           System.out.println(vo);
            return Result.getSuccess(vo);
        }catch (Exception e){
            throw new GlobalException(FailedMessage.bindFailed("服务端异常"));
        }
    }

    @RequestMapping("/killthegoods")
    @ResponseBody
    public Result  miaoshaController(@RequestParam("goodsId")String goodsId,miaoshaUser user){
        if(user==null)throw new GlobalException(FailedMessage.UNlOGIN);
        boolean falg=goodsMap.get(goodsId);
        if(falg){
            throw new GlobalException(FailedMessage.bindFailed("手速太慢，没商品已抢光"));
        }
        //判断商品是否已经售空
        if(redisService.decrKey(MiaoshaGoodsKey.getMiaoShaGoodsKey(goodsId),1)<0){
            goodsMap.put(goodsId,true);
            throw new GlobalException(FailedMessage.bindFailed("手速太慢，没商品已抢光"));
        }
        //检查是否是重复秒杀
        MiaoshaOrderVo vo=new MiaoshaOrderVo(user.getId(),goodsId);
        miaoshaOrder morder=mservice.getmiaoshaOrder(vo);
        if(morder!=null)throw  new GlobalException(FailedMessage.bindFailed("做人不能太贪心，您已经秒杀过该商品"));
        //入队
        miaoshaMessage miaoshaMessage=new miaoshaMessage();
        miaoshaMessage.setUser(user);
        miaoshaMessage.setGoodsId(goodsId);
        mqSender.miaoshaSender(miaoshaMessage);
        return Result.getSuccess(0);//排队

    }

    @RequestMapping("/miaosha/result")
    @ResponseBody
    public Result getMiaoShaResult(@RequestParam("goodsId")String goodsId,miaoshaUser user){
        if(user==null) if(user==null)throw new GlobalException(FailedMessage.UNlOGIN);
        //检查是存在该用户的秒杀订单
        MiaoshaOrderVo vo=new MiaoshaOrderVo(user.getId(),goodsId);
        miaoshaOrder morder=mservice.getmiaoshaOrder(vo);
        if(morder==null){
            boolean flag=mservice.getOverSell();
            if(flag)return Result.getSuccess(-1);//秒杀失败
            else return Result.getSuccess(0);//还在排队

        }
        else return Result.getSuccess(morder.getOrderId());//秒杀成功
    }

    @RequestMapping("/miaosha.do") //秒杀
    @ResponseBody
    public Result<String>  doMiaoSha(@RequestParam("goodsId")String goodsId, miaoshaUser user){
        //检查是否登录
        if(user==null)throw new GlobalException(FailedMessage.bindFailed("您尚未登录，登录后才能参与活动"));
       // System.out.println("goosId: "+goodsId+", user:"+user);
        //检查是否是重复秒杀
        MiaoshaOrderVo vo=new MiaoshaOrderVo(user.getId(),goodsId);
        miaoshaOrder morder=mservice.getmiaoshaOrder(vo);
        if(morder!=null)throw  new GlobalException(FailedMessage.bindFailed("做人不能太贪心，您已经秒杀过该商品"));
        //检查是否还有库存
        goodsVo goods=mgservice.getGoodsVoFoeMiaosha(goodsId);
        if(goods==null)throw new GlobalException(FailedMessage.bindFailed("没有该商品"));
        if(goods.getStockCount()<=0)throw new GlobalException(FailedMessage.bindFailed("手速太慢，没商品已抢光"));
        //创建秒杀订单
        miaoshaOrder newOrder=mservice.createOneMiaoshaOrder(goods,user);
        if(newOrder==null)return Result.getFailed (FailedMessage.BLACKMIAOSHAERROR);
            return Result.getSuccess(newOrder.getOrderId());
    }
    @RequestMapping("/turnOrderDetail.do") //订单详情
    @ResponseBody
    public Result<OrderDetialVo> turnOrderDetail(miaoshaUser user,@RequestParam("orderId") String orderId){
            if(user==null)throw  new GlobalException(FailedMessage.UNlOGIN);
            if(StringUtils.isEmpty(orderId))new GlobalException(FailedMessage.bindFailed("没有该商品"));
            orderInfo info=infoService.getById(orderId);
            goodsVo goods=service.getGoodsById(info.getGoodsId());
            if(goods==null)throw new GlobalException(FailedMessage.bindFailed("没有该商品"));
            OrderDetialVo orderDetial=new OrderDetialVo();
            if(orderDetial==null)throw new GlobalException(FailedMessage.bindFailed("没有该订单"));
            orderDetial.setGoods(goods);
            orderDetial.setInfo(info);
            orderDetial.setmUser(user);
             return Result.getSuccess(orderDetial);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //容器启动完成配置后执行的任务    --将商品数量缓存入redis
            List<MiaoshaGoodsCount> list=mgservice.getAllCount();
            if(!redisService.containKey(GoodsKey.getgoodsListKey("glist"))){
                List<goodsVo> goodsVoList =service.getAllGoods();
                redisService.setList(GoodsKey.getgoodsListKey("glist"),goodsVoList);
            }
            if(list==null)return;
            for (MiaoshaGoodsCount c:list) {
                redisService.setString(MiaoshaGoodsKey.getMiaoShaGoodsKey(c.getGoodsId()),c);
                goodsMap.put(c.getGoodsId(),false);//内存标记
            }
        }

}
