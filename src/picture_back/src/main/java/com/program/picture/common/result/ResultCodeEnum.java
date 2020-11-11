package com.program.picture.common.result;

/**
 * @program: itta
 * @description: 统一返回格式枚举
 * @author: Mr.Huang
 * @create: 2020-04-04 10:37
 **/
public enum ResultCodeEnum {
    /*** 通用部分 100 - 599***/
    // 成功请求
    SUCCESS(200, "successful"),
    // 重定向
    REDIRECT(301, "redirect"),
    // 资源未找到
    NOT_FOUND(404, "not found"),
    // 服务器错误
    SERVER_ERROR(500, "server error"),
    /*** 这里可以根据不同模块用不同的区级分开错误码，例如:  ***/
    // 1000～1999 区间表示用户模块错误
    User_Exists_Exception(10001, "用户已存在"),
    User_Not_Exists_Exception(10002, "用户不存在"),
    User_Login_Fail(10003, "用户名或密码错误"),
    User_Change_Password_Fail(10004, "旧密码错误"),
    User_Follow_Fail(10005, "该用户已关注"),
    User_Follow_Cancel_Fail(10006, "该用户还未关注"),
    User_Add_Type_Fail(10007,"该图片标签已添加"),
    User_Del_Type_Fail(10008,"该图片还未添加"),
    // 2000～2999 区间表示图片模块错误
    Picture_Del_Fail_Exception(20001, "图片删除失败"),
    Picture_Add_Fail_Exception(20002, "图片添加失败"),
    Picture_Update_Fail_Exception(20003, "图片更新失败"),
    Picture_Select_Fail_Exception(20004, "图片查找失败"),
    // 3000～3999 区间表示标签模块错误
    PictureType_Add_Fail_Exception(30001, "图片标签添加失败"),
    PictureType_Del_Fail_Exception(30002, "图片标签删除失败"),
    // 4000～4999 区间表示收藏模块错误
    PictureCollection_Add_Fail_Exception(40001, "图片收藏添加失败"),
    PictureCollection_Del_Fail_Exception(40002, "图片收藏删除失败"),
    GalleryCollection_Add_Fail_Exception(40003, "图库收藏添加失败"),
    GalleryCollection_Del_Fail_Exception(40004, "图库收藏删除失败"),
    // 5000～5999 区间表示喜欢模块错误
    PictureLike_Add_Fail_Exception(50001, "图片喜欢添加失败"),
    PictureLike_Del_Fail_Exception(50002, "图片喜欢删除失败"),
    GalleryLike_Add_Fail_Exception(50003, "图库喜欢添加失败"),
    GalleryLike_Del_Fail_Exception(50004, "图库喜欢删除失败"),
    // 6000～6999 区间表示图库模块错误
    Gallery_Del_Fail_Exception(60001, "图库删除失败"),
    Gallery_Add_Fail_Exception(60002, "图库添加失败"),
    Gallery_Update_Fail_Exception(60003, "图库更新失败"),
    Gallery_Select_Fail_Exception(60004, "图库查找失败"),


    ;

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
