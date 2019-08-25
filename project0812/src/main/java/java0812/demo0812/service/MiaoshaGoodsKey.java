package java0812.demo0812.service;

public class MiaoshaGoodsKey extends BasePrefix {

    private static final int miaoshaGoodsLimitTime=3600;

    private MiaoshaGoodsKey(String goodsid) {
        super(miaoshaGoodsLimitTime, goodsid);
    }


    public static MiaoshaGoodsKey getMiaoShaGoodsKey(String goodsId){
        return new MiaoshaGoodsKey(goodsId);
    }
}
