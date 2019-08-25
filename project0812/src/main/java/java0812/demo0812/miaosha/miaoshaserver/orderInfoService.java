package java0812.demo0812.miaosha.miaoshaserver;

import java0812.demo0812.miaosha.sqldao.orderInfoDao;
import java0812.demo0812.miaosha.sqlvo.orderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class orderInfoService {

    @Autowired
    orderInfoDao dao;

    @Transactional
    public void  insertOne(orderInfo info){
        dao.inserOneOrderInfo(info);
    }

    @Transactional
    public orderInfo getById(String orderId){
        return dao.getOneOrderInfo(orderId);
    }

}
