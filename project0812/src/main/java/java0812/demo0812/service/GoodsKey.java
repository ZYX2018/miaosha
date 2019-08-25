package java0812.demo0812.service;

public class GoodsKey extends BasePrefix {
    private  static int goodsListLimited=3600;

    private GoodsKey(int limtTime, String prefix) {
        super(limtTime, prefix);
    }

    public static GoodsKey getgoodsListKey(String lisName){
        return new GoodsKey(goodsListLimited,lisName);
    }
}
