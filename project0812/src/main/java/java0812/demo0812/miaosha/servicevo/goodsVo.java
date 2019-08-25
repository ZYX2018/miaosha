package java0812.demo0812.miaosha.servicevo;

import java0812.demo0812.miaosha.sqlvo.Goods;

import java.util.Date;


public class goodsVo extends Goods {
    private    double miaoshaPrice;
    private    int  stockCount;
    private Date startDate;
    private    Date endDate;

    public goodsVo() {
    }

    public goodsVo(String goodsId, String goodsName, String goodsTitle, String goodsImg, String goodsDetail, double goodsPrice, int goodsStock, double miaoshaPrice, int stockCount, Date startDate, Date endDate) {
        super(goodsId, goodsName, goodsTitle, goodsImg, goodsDetail, goodsPrice, goodsStock);
        this.miaoshaPrice = miaoshaPrice;
        this.stockCount = stockCount;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "goodsVo{" +
                "miaoshaPrice=" + miaoshaPrice +
                ", stockCount=" + stockCount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}'+super.toString();
    }
}
