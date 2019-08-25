package java0812.demo0812.miaosha.servicevo;

public class MiaoshaOrderVo {
    String userId;
    String goodsId;

    public MiaoshaOrderVo(String userId, String goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MiaoshaOrderVo{" +
                "userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                '}';
    }
}
