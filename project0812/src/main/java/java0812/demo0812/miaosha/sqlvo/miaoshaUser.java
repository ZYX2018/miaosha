package java0812.demo0812.miaosha.sqlvo;

import java0812.demo0812.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.sql.Date;

public class miaoshaUser {

    @NotNull
    @IsMobile
    String id; //手机号
    String nickName ; //用户名
    @NotNull
    @Length(min = 32)
    String password ;//(md5(pass明文+固定salt)+salt)',
    String salt ;//
    String head;// '头像,云储存的id',
    Date registerDate;// '注册时间',
    Date lastLoginDate;//  '上次登录时间',
    int loginCount;// '登录次数',
    String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public miaoshaUser() {
    }

    public miaoshaUser(String id, String nickName, String password, String salt, String head, Date registerDate, Date lastLoginDate, int loginCount) {
        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.salt = salt;
        this.head = head;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "miaoshaUser{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", registerDate=" + registerDate +
                ", lastLoginDate=" + lastLoginDate +
                ", loginCount=" + loginCount +
                ", address='" + address + '\'' +
                '}';
    }
}
