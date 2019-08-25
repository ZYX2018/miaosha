package java0812.demo0812.vo;

public class Result<T> {
    private int code;
    private String message;
    private T data=null;
    private Result(T data){
        this.code=0;
        this.message="登录成功";
        this.data=data;
    }
    private Result(FailedMessage m){
        this.code=m.getCode();
        this.message=m.getMessage();
    }
    public  static <T>Result<T>  getSuccess(T data ){
        return new Result<T>(data);
    }
    public  static Result getFailed(FailedMessage message ){
        return new Result(message);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
