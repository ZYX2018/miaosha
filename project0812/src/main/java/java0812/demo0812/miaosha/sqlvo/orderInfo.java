package java0812.demo0812.miaosha.sqlvo;

import java.util.Date;

public class orderInfo {

    private  String id;
    private  String userId;
    private  String goodsId;
    private  String deliveryAddrId;
    private  String goodsName;
    private  int goodsCount;
    private  double goodsPrice;
    private  int orderChannel;
    private  int  status;
    private  Date createDate;
    private  Date payDate;

    public orderInfo() {
    }

    public orderInfo( String userId, String goodsId, String deliveryAddrId, String goodsName, int goodsCount, double goodsPrice, int orderChannel, int status, Date createDate, Date payDate) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.deliveryAddrId = deliveryAddrId;
        this.goodsName = goodsName;
        this.goodsCount = goodsCount;
        this.goodsPrice = goodsPrice;
        this.orderChannel = orderChannel;
        this.status = status;
        this.createDate = createDate;
        this.payDate = payDate;
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getDeliveryAddrId() {
        return deliveryAddrId;
    }

    public void setDeliveryAddrId(String deliveryAddrId) {
        this.deliveryAddrId = deliveryAddrId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(int orderChannel) {
        this.orderChannel = orderChannel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "orderInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", deliveryAddrId='" + deliveryAddrId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsCount=" + goodsCount +
                ", goodsPrice=" + goodsPrice +
                ", orderChannel=" + orderChannel +
                ", status=" + status +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                '}';
    }
}
