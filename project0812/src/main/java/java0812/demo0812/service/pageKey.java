package java0812.demo0812.service;

public class pageKey extends BasePrefix {
    public  static int pageLimtTime =60;//页面缓存寿命
    public pageKey(int limtTime, String prefix) {
        super(limtTime, prefix);
    }
    public static pageKey getPageKey(String pageName){
        return new pageKey(pageLimtTime,pageName);
    }
    //goodsList页面
    public static pageKey getGlistPageKey(){ return new pageKey(pageLimtTime,"gl"); }
    //商品详情页面
    public  static pageKey getGDetailKey(String goodsId){
        return new pageKey(pageLimtTime,"goodsDetail:"+goodsId);
    }

}
