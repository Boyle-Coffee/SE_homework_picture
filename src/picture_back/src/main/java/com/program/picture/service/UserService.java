package com.program.picture.service;

import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.User;
import com.program.picture.domain.entity.UserDetails;
/**
 * @auther: Bluebear
 * @date: 2020/11/09/18:50
 * @description: 用户的操作接口
 */
public interface UserService {
    ResultCodeEnum userRegister(User user);

}
