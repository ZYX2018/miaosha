package java0812.demo0812.miaosha.miaoshaserver;

import java0812.demo0812.miaosha.servicevo.MiaoshaGoodsCount;
import java0812.demo0812.miaosha.servicevo.goodsVo;
import java0812.demo0812.miaosha.sqldao.miaoshaGoosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class miaoshaGooodsService {

@Autowired
miaoshaGoosDao dao;
@Autowired
goodsService gservice;
@Transactional
public boolean  updateCount(String goodsId ){
   int re= dao.upddateGoods(goodsId);
   gservice.updateSumCount(goodsId);
   return re>0;
}

@Transactional(readOnly = true)
public  int getStokenCount(String goosId){
    return dao.getStokenCount(goosId);
}
@Transactional(readOnly = true)
public goodsVo getGoodsVoFoeMiaosha(String goodsId){
    return dao.getGoodsformiaosha(goodsId);
}

@Transactional(readOnly = true)
public List<MiaoshaGoodsCount> getAllCount(){
    return dao.getAllMiaoshaGoodsCount();
}

}
