package java0812.demo0812.miaosha.sqldao;

import java0812.demo0812.miaosha.sqlvo.orderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface orderInfoDao {
    @Select("SELECT * FROM orderinfo WHERE id=#{orderId}")
    public orderInfo getOneOrderInfo(String orderId);


    public void  inserOneOrderInfo(orderInfo goods);

}
