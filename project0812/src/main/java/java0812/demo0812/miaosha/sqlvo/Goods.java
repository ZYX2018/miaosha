package java0812.demo0812.miaosha.sqlvo;

import com.mysql.jdbc.StringUtils;

public class Goods {
  private    String goodsId;
  private    String goodsName;
  private    String goodsTitle;
  private    String goodsImg;
  private    String goodsDetail;
  private    double goodsPrice;
  private    int  goodsStock;

    public Goods(String goodsId, String goodsName, String goodsTitle, String goodsImg, String goodsDetail, double goodsPrice, int goodsStock) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsTitle = goodsTitle;
        this.goodsImg = goodsImg;
        this.goodsDetail = goodsDetail;
        this.goodsPrice = goodsPrice;
        this.goodsStock = goodsStock;
    }

    public Goods() {
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsTitle='" + goodsTitle + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsStock=" + goodsStock +
                '}';
    }
}
