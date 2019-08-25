package java0812.demo0812.miaosha.servicevo;

import java0812.demo0812.miaosha.sqlvo.miaoshaUser;
import java0812.demo0812.miaosha.sqlvo.orderInfo;

public class OrderDetialVo {

    private goodsVo goods;
    private orderInfo info;
    private miaoshaUser mUser;

    public OrderDetialVo() {
    }

    public miaoshaUser getmUser() {
        return mUser;
    }

    public void setmUser(miaoshaUser mUser) {
        this.mUser = mUser;
    }

    public goodsVo getGoods() {
        return goods;
    }

    public void setGoods(goodsVo goods) {
        this.goods = goods;
    }

    public orderInfo getInfo() {
        return info;
    }

    public void setInfo(orderInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "OrderDetialVo{" +
                "goods=" + goods +
                ", info=" + info +
                ", mUser=" + mUser +
                '}';
    }
}
