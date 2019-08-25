package java0812.demo0812.service;

public abstract class  BasePrefix implements KeyPrefix {


    int limtTime; //过期时间s
    String prefix;

    public BasePrefix(int limtTime, String prefix) {
        this.limtTime = limtTime;
        this.prefix = prefix;
    }

    @Override
    public int limitTime() {
        return this.limtTime;
    }

    @Override
    public String getKeyPrefix() {
        String className=getClass().getSimpleName();
        return className+":"+this.prefix;
    }
}
