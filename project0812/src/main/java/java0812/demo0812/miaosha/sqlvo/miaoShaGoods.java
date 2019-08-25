package java0812.demo0812.miaosha.sqlvo;

import java.sql.Date;

public class miaoShaGoods  {

 private    String id;
 private    String goodsId;
 private    double miaoshaPrice;
 private    int  stockCount;
 private    Date startDate;
 private    Date endDate;

    public miaoShaGoods(String id, String goodsId, double miaoshaPrice, int stockCount, Date startDate) {
        this.id = id;
        this.goodsId = goodsId;
        this.miaoshaPrice = miaoshaPrice;
        this.stockCount = stockCount;
        this.startDate = startDate;
    }

    public miaoShaGoods() {
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public double getMiaoshaPrice() {
        return miaoshaPrice;
    }

    public void setMiaoshaPrice(double miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "miaoShaGoods{" +
                "id='" + id + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", miaoshaPrice=" + miaoshaPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
