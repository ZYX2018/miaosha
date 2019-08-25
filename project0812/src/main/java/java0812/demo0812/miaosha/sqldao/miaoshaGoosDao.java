package java0812.demo0812.miaosha.sqldao;

import java0812.demo0812.miaosha.servicevo.MiaoshaGoodsCount;
import java0812.demo0812.miaosha.servicevo.goodsVo;
import java0812.demo0812.miaosha.sqlvo.Goods;
import java0812.demo0812.miaosha.sqlvo.miaoShaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface miaoshaGoosDao {
    int i=1;
    @Update("UPDATE miaoshagoods g SET g.stockCount=g.stockCount-1 WHERE g.goodsId=#{goodsId} AND g.stockCount>0;")
    public int upddateGoods(@Param("goodsId")String goodsId);
    @Select("SELECT m.stockCount  FROM miaoshagoods m WHERE m.goodsId=#{goodsId}")
    public int getStokenCount(@Param("goodsId")String goodsId);
    @Select("SELECT  g.goodsId,g.goodsName,mg.miaoshaPrice,mg.stockCount  FROM goods g LEFT JOIN miaoshagoods mg ON g.goodsId=mg.goodsId WHERE g.goodsId=#{goodsId}")
    public goodsVo getGoodsformiaosha(@Param("goodsId")String goodsId);
    @Select("SELECT goodsId,stockCount  FROM miaoshagoods ")
    public List<MiaoshaGoodsCount> getAllMiaoshaGoodsCount();
}
