package java0812.demo0812.miaosha.servicevo;

import java0812.demo0812.miaosha.sqlvo.miaoshaUser;

public class miaoshaMessage {

    private miaoshaUser user;
    private String goodsId;

    public miaoshaMessage() {
    }

    public miaoshaUser getUser() {
        return user;
    }

    public void setUser(miaoshaUser user) {
        this.user = user;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "miaoshaMessage{" +
                "user=" + user +
                ", goodsId='" + goodsId + '\'' +
                '}';
    }
}
