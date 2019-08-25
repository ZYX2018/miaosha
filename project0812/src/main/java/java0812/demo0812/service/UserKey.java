package java0812.demo0812.service;

import java0812.demo0812.miaosha.controllers.LoginController;

public class UserKey extends BasePrefix {


   private  UserKey(String prefix) {
        super(3600,prefix);
    }

    private UserKey(int limtTime, String prefix) {
        super(limtTime, prefix);
    }
    public  static UserKey getUserKeyById(String id){ return  new UserKey("id: "+id); }
    public  static UserKey getUserKeyById(int id,int time){ return  new UserKey(time, "id: "+id); }
    public  static UserKey getUserKeyByName(String name){ return  new UserKey("name:"+name); }
    public  static UserKey getUserKeyByName(int time,String name){ return  new UserKey(time,"name: "+name); }
    public  static UserKey userToken(String name){return  new UserKey(LoginController.CookieAndTokenLimitTime,"token:"+name);}
}
