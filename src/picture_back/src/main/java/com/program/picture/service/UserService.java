package com.program.picture.service;

import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.User;
import com.program.picture.domain.entity.UserDetails;

import java.util.List;

/**
 * @auther: Blue bear
 * @date: 2020/11/09/18:50
 * @description: 用户的操作接口
 */
public interface UserService {
    ResultCodeEnum userRegister(String userName,String password);

    ResultCodeEnum userLogin(String userName,String password);

    ResultCodeEnum userChangePassword(String userName,String oldPassword,String newPassword);

    ResultCodeEnum userChangeDetails(UserDetails details);

    UserDetails userGetDetails(Integer userId);

    ResultCodeEnum userFollow(Integer userId, Integer followId);

    ResultCodeEnum userFollowCancel(Integer userId, Integer followId);

    ResultCodeEnum userAddLikeType(Integer userId, Integer typeId);

    List<Integer> userGetLikeType(Integer userId);

    ResultCodeEnum userDeleteLikeType(Integer userId, Integer typeId);
}
