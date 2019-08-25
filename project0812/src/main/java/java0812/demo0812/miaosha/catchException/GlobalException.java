package java0812.demo0812.miaosha.catchException;

import java0812.demo0812.vo.FailedMessage;
/*
定义全局业务异常
 */

public class GlobalException extends RuntimeException {

   private FailedMessage failed;

    public GlobalException(FailedMessage failed) {
        this.failed = failed;
    }


    public FailedMessage getFailed() {
        return failed;
    }

    public void setFailed(FailedMessage failed) {
        this.failed = failed;
    }
}
