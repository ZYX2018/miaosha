package java0812.demo0812.service;

public class OrderKey extends  BasePrefix{

    private  static int orderCacheLimitTime=3600;

    private  OrderKey(int tme,String prefix){
        super(tme,prefix);
    }
    private  OrderKey(String prefix){
        super(orderCacheLimitTime,prefix);
    }
    public static OrderKey getmiaoshaOrderKeyId(String id){
        return  new OrderKey("order: "+id);
    }
    public static OrderKey getOverFlagKey(){
        return new OrderKey("flag");
    }

    public static OrderKey getmiaoshaOrderKeyId(int time,String id){
        return  new OrderKey(time,"order: "+id);
    }
    public static OrderKey getmiaoshaOrderKeyByName(String name){
        return  new OrderKey("name: "+name);
    }
    public static OrderKey getmiaoshaOrderKeyByName(int time,String name){
        return  new OrderKey(time,"name: "+name);
    }
    public static OrderKey getorderInfoKeyById(String infoId){ return  new OrderKey(orderCacheLimitTime,infoId);}


}
