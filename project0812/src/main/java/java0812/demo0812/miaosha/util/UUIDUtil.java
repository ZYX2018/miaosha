package java0812.demo0812.miaosha.util;

import java.util.UUID;

public class UUIDUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");//原生的UUID带- 想用别的字符替换
    }
}
