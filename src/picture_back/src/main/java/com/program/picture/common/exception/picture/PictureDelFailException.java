package com.program.picture.common.exception.picture;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-10 22:13
 **/
public class PictureDelFailException extends RuntimeException {
    /**
     * 错误码
     */
    protected Integer code;

    protected String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 有参构造器，返回码在枚举类中，这里可以指定错误信息
     *
     * @param msg
     */
    public PictureDelFailException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
