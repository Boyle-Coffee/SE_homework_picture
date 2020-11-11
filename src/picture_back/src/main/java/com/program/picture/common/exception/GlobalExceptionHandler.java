package com.program.picture.common.exception;

import com.program.picture.common.exception.collection.PictureCollectionAddFailException;
import com.program.picture.common.exception.collection.PictureCollectionDelFailException;
import com.program.picture.common.exception.picture.PictureAddFailException;
import com.program.picture.common.exception.picture.PictureDelFailException;
import com.program.picture.common.exception.picture.PictureSelectFailException;
import com.program.picture.common.exception.picture.PictureUpdateFailException;
import com.program.picture.common.exception.type.PictureTypeAddFailException;
import com.program.picture.common.exception.type.PictureTypeDelFailException;
import com.program.picture.common.result.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.program.picture.common.result.ResultCodeEnum.*;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-10 22:10
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*** 这里可以根据不同模块分开错误***/
    // 图片模块
    @ExceptionHandler(value = PictureDelFailException.class)
    public HttpResult pictureDelFailExceptionHandler(PictureDelFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(Picture_Del_Fail_Exception);
    }

    @ExceptionHandler(value = PictureAddFailException.class)
    public HttpResult pictureAddFailExceptionHandler(PictureAddFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(Picture_Add_Fail_Exception);
    }

    @ExceptionHandler(value = PictureUpdateFailException.class)
    public HttpResult pictureUpdateFailExceptionHandler(PictureUpdateFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(Picture_Update_Fail_Exception);
    }

    @ExceptionHandler(value = PictureSelectFailException.class)
    public HttpResult pictureSelectFailExceptionHandler(PictureSelectFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(Picture_Select_Fail_Exception);
    }

    // 标签模块
    @ExceptionHandler(value = PictureTypeAddFailException.class)
    public HttpResult pictureTypeAddFailExceptionHandler(PictureTypeAddFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(PictureType_Add_Fail_Exception);
    }

    @ExceptionHandler(value = PictureTypeDelFailException.class)
    public HttpResult pictureTypeDelFailExceptionHandler(PictureTypeDelFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(PictureType_Del_Fail_Exception);
    }

    // 收藏模块
    @ExceptionHandler(value = PictureCollectionAddFailException.class)
    public HttpResult pictureCollectionAddFailExceptionHandler(PictureCollectionAddFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(PictureCollection_Add_Fail_Exception);
    }

    @ExceptionHandler(value = PictureCollectionDelFailException.class)
    public HttpResult pictureCollectionDelFailExceptionHandler(PictureCollectionDelFailException e) {
        logger.error("发生业务异常！原因是：{}", e.getMsg());
        return HttpResult.failure(PictureCollection_Del_Fail_Exception);
    }
}
