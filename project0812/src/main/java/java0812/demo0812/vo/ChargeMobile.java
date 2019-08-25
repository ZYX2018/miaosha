package java0812.demo0812.vo;

import com.mysql.jdbc.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChargeMobile {

    private static final Pattern is_MOBILE= Pattern.compile("1\\d{10}");

    public static boolean isMobile(String mobile){
        if(StringUtils.isEmptyOrWhitespaceOnly(mobile)){
            return false;
        }
        Matcher matcher=is_MOBILE.matcher(mobile);
        return matcher.matches();
    }
}
