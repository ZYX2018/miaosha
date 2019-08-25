package java0812.demo0812.vo;

public class FailedMessage {
    private int code;
    private String message;
    private FailedMessage(int i,String m){
        this.code=i;
        this.message=m;
    }
    public static FailedMessage CliFailed=new FailedMessage(404,"客户端错误");
    public static FailedMessage ServerFailed=new FailedMessage(500,"服务端错误");
    public static FailedMessage NULLPASSWD=new FailedMessage(50001,"密码输入不能为空");
    public static FailedMessage NULLMOBILE=new FailedMessage(50002,"号码输入不能为空");
    public static FailedMessage ERROPASSWD=new FailedMessage(50003,"密码错误");
    public static FailedMessage ERRORMOBILE=new FailedMessage(50004,"手机号码有误");
    public static FailedMessage USERNOTEXIST=new FailedMessage(50005,"用户不存在");
    public static FailedMessage INPUTNULLERROR=new FailedMessage(50006,"输入不能为空");
    public static FailedMessage NOTKNOWERROR=new FailedMessage(-2,"未知异常");
    public static FailedMessage BLACKMIAOSHAERROR=new FailedMessage(-3,"脸黑秒杀失败请重试");
    public static FailedMessage UNlOGIN=new FailedMessage(-4,"您尚未登录");

    public static  FailedMessage bindFailed(Object... args){
        String message=String.format("%s",args);
        return new FailedMessage(-1,message);
    }



    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
