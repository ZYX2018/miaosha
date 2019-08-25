package java0812.demo0812.miaosha.miaoshaserver;

import java0812.demo0812.miaosha.servicevo.goodsVo;
import java0812.demo0812.miaosha.sqldao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class goodsService {

    @Autowired
    GoodsDao dao;

    @Transactional(readOnly = true)
    public List<goodsVo>  getAllGoods(){
        return  dao.getAllGoos();
    }

    @Transactional(readOnly = true)
    public goodsVo getGoodsById(String id){
        return dao.getgoodsVoById(id);
    }

    @Transactional(readOnly = true)
    public int getSumCount(String goodsId){
        return dao.getsumCount(goodsId);
    }

    @Transactional
    public void  updateSumCount(String goodsId){
        dao.upddateGoodsCount(goodsId);
    }

}




