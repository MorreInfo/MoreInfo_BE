package cn.edu.fudan.moreinfo.ebook.common;

/**
 * Created by benull on 2020/11/13
 */

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),   //成功
    ERROR(1,"ERROR"),       //失败
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),   //非法参数
    NEED_LOGIN(10,"NEED_LOGIN"),   //未登录
    ACCESS_DENIED(20,"ACCESS DENIED");  //权限不足

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {

        return code;
    }

    public String getDesc() {
        return desc;
    }
}
