package java0812.demo0812.miaosha.validator;


import java0812.demo0812.vo.ChargeMobile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
   private  boolean flag=false;
    @Override
    public void initialize(IsMobile constraintAnnotation) { //校验器接受的参数是否可以为空
            flag=constraintAnnotation.require();
    }

    @Override //校验方法
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (flag){
            return ChargeMobile.isMobile(value);
        }else {
           return ChargeMobile.isMobile(value);
        }

    }
}
