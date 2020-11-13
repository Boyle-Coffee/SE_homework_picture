package com.program.picture.service;

import com.program.picture.common.result.HttpResult;

/**
 * @Auther: Bluebear
 * @Date: 2020/11/13/20:02
 * @Description:
 */
public interface LikeTypeService {
    HttpResult AddLikeType(Integer userId, Integer typeId);

    HttpResult SelectLikeType(Integer userId);

    HttpResult DeleteLikeType(Integer userId, Integer typeId);
}
