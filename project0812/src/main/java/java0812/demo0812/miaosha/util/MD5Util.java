package java0812.demo0812.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class MD5Util {

    private final static String salt="as3687s";

    /*
    第一次加密
     */
    public static String mdString(String pass){
        return DigestUtils.md5Hex(pass);
    }

    public static String inputPassToFormPass(String  inputPass){
        String temp=inputPass+salt;
        return DigestUtils.md5Hex(temp);
    }

    /*
    第二次加密
     */
    public  static String formPassToDbPass(String inputPass,String salt){
            return DigestUtils.md5Hex(inputPassToFormPass(inputPass)+salt);
    }

}
