package com.program.picture.service;

import com.program.picture.domain.entity.UserDetails;
import com.program.picture.common.result.HttpResult;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @auther: Blue bear
 * @date: 2020/11/09/18:50
 * @description: 用户的操作接口
 */
public interface UserService {
    HttpResult userRegister(String userName,String password);

    HttpResult userLogin(String userName,String password);

    HttpResult userGetIdByName(String userName);

    HttpResult userUpdatePassword(Integer userId, String oldPassword, String newPassword);

    HttpResult userUpdateUserName(Integer userId, String userName);

    HttpResult userUpdateDetails(UserDetails details);

    HttpResult userSelectDetails(Integer userId);

}
