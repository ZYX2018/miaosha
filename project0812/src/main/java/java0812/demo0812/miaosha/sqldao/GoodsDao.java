package java0812.demo0812.miaosha.sqldao;

import java0812.demo0812.miaosha.servicevo.goodsVo;
import java0812.demo0812.miaosha.sqlvo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface GoodsDao {

@Select("SELECT  g.* ,mg.miaoshaPrice ,mg.stockCount,mg.startDate,mg.endDate  FROM goods g LEFT JOIN miaoshagoods mg ON g.goodsId=mg.goodsId")
public List<goodsVo> getAllGoos();
@Select("SELECT  g.* ,mg.miaoshaPrice ,mg.stockCount,mg.startDate,mg.endDate  FROM goods g LEFT JOIN miaoshagoods mg ON g.goodsId=mg.goodsId where mg.goodsId=#{id}")
public  goodsVo getgoodsVoById(@Param("id")String id);

@Update("UPDATE goods g SET g.goodsStock=g.goodsStock-1 WHERE g.goodsId=#{goodsId} AND g.goodsStock>0 ")
public void upddateGoodsCount(@Param("goodsId")String goodsId);
@Select("SELECT   g.goodsStock FROM goods g WHERE g.goodsId=#{goodsId}")
public  int getsumCount(String goodsId);


}
