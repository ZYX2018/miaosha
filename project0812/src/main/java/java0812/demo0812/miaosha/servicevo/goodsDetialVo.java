package java0812.demo0812.miaosha.servicevo;

import java0812.demo0812.miaosha.sqlvo.miaoshaUser;

public class goodsDetialVo {
    private miaoshaUser msUser;
    private goodsVo gvo;
    int miaoshaStatus;
    long remainSeconds;

    public goodsDetialVo() {
    }

    public miaoshaUser getMsUser() {
        return msUser;
    }

    public void setMsUser(miaoshaUser msUser) {
        this.msUser = msUser;
    }

    public goodsVo getGvo() {
        return gvo;
    }

    public void setGvo(goodsVo gvo) {
        this.gvo = gvo;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public long getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(long remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    @Override
    public String toString() {
        return "goodsDetialVo{" +
                "msUser=" + msUser +
                ", gvo=" + gvo +
                ", miaoshaStatus=" + miaoshaStatus +
                ", remainSeconds=" + remainSeconds +
                '}';
    }
}
