package java0812.demo0812.miaosha.sqldao;

import java0812.demo0812.miaosha.servicevo.MiaoshaOrderVo;
import java0812.demo0812.miaosha.sqlvo.miaoshaOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface miaoshaOrderDao {

public void insertOneMiaoshaOrder(miaoshaOrder order);//创建订单

public void deleteOne();//取消订单
    @Select("SELECT orderId FROM miaoshaorder m  WHERE m.userId=#{userId} and m.goodsId=#{goodsId}")
public miaoshaOrder selectOneMiaoshaOrder(MiaoshaOrderVo vo);//查询秒杀订单

}
