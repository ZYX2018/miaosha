package java0812.demo0812.miaosha.servicevo;

public class MiaoshaGoodsCount {

    private    String goodsId;
    private    int  stockCount;

    public MiaoshaGoodsCount() {
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MiaoshaGoodsCount{" +
                "goodsId='" + goodsId + '\'' +
                ", stockCount=" + stockCount +
                '}';
    }
}
