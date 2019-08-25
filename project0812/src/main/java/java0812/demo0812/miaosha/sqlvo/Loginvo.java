package java0812.demo0812.miaosha.sqlvo;

import java0812.demo0812.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class Loginvo {
    @NotNull
    @IsMobile
    String id; //手机号

    @NotNull
    @Length(min = 32)
    String password ;//(md5(pass明文+固定salt)+salt)',

    public Loginvo(@NotNull @IsMobile String id, @NotNull @Length(min = 32) String password) {
        this.id = id;
        this.password = password;
    }

    public Loginvo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Loginvo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
