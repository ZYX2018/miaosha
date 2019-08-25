package java0812.demo0812.miaosha.sqlvo;

public class miaoshaOrder {

    private String id;
    private String userId;
    private String orderId;
    private String goodsId;
    private orderInfo info;
    public miaoshaOrder(String id, String userId, String orderId, String goodsId, orderInfo info) {
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.info = info;
    }


    public miaoshaOrder() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public orderInfo getInfo() {
        return info;
    }

    public void setInfo(orderInfo info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "miaoshaOrder{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", info=" + info +
                '}';
    }
}
