package java0812.demo0812.miaosha.catchException;

import java0812.demo0812.vo.FailedMessage;
import java0812.demo0812.vo.Result;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {
/*
全局异常捕获处理
 */
    @ExceptionHandler(value =Exception.class ) //拦截异常类型
    public Result<String> exceptionHandle(HttpServletRequest request,Exception e){

        if(e instanceof GlobalException){
            GlobalException exception=(GlobalException)e;
            return Result.getFailed(exception.getFailed());
        }else if(e instanceof BindException){
            BindException exception=(BindException) e;
            List<ObjectError> errors=exception.getAllErrors();
            ObjectError error=errors.get(0);
        return Result.getFailed(FailedMessage.bindFailed(error.getDefaultMessage()));
        }else {
            e.printStackTrace();
            return  Result.getFailed(FailedMessage.NOTKNOWERROR);
        }
    }

}
