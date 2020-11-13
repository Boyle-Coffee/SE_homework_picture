package com.program.picture.service.Impl;


import com.program.picture.common.result.HttpResult;
import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.User;
import com.program.picture.domain.entity.UserDetails;
import com.program.picture.domain.entity.UserFollow;
import com.program.picture.domain.entity.UserLikeType;
import com.program.picture.mapper.UserDetailsMapper;
import com.program.picture.mapper.UserFollowMapper;
import com.program.picture.mapper.UserLikeTypeMapper;
import com.program.picture.mapper.UserMapper;
import com.program.picture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther: Blue bear
 * @date: 2020/11/09/19:02
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsMapper userDetailsMapper;




    @Override
    public HttpResult userRegister(String userName, String password) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return HttpResult.failure(ResultCodeEnum.User_Exists_Exception);
            }
        }

        User user = User.builder()
                .name(userName)
                .password(password)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userMapper.insert(user);

        UserDetails details = UserDetails.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userDetailsMapper.insert(details);

        return HttpResult.success();
    }

    @Override
    public HttpResult userLogin(String userName, String password) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getPassword().equals(password)) {
                    return HttpResult.success();
                } else {
                    return HttpResult.failure(ResultCodeEnum.User_Login_Fail_Exception);
                }
            }
        }
        return HttpResult.failure(ResultCodeEnum.User_Not_Exists_Exception);
    }

    @Override
    public HttpResult userUpdatePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            user.setUpdateTime(new Date());
            userMapper.updateByPrimaryKey(user);
            return HttpResult.success();
        } else {
            return HttpResult.failure(ResultCodeEnum.User_Update_Password_Fail_Exception);
        }
    }

    @Override
    public HttpResult userUpdateUserName(Integer userId, String userName) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return HttpResult.failure(ResultCodeEnum.User_Exists_Exception);
            }
        }
        User user = userMapper.selectByPrimaryKey(userId);
        user.setName(userName);
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);
        return HttpResult.success();
    }


    @Override
    public HttpResult userSelectDetails(Integer userId) {
        List<UserDetails> userDetails = userDetailsMapper.selectAll();
        for (UserDetails userDetail : userDetails) {
            if (userDetail.getUserId().equals(userId)) {
                return HttpResult.success(userDetail);
            }
        }
        return HttpResult.failure(ResultCodeEnum.User_Not_Exists_Exception);
    }

    @Override
    public HttpResult userUpdateDetails(UserDetails details) {
        details.setUpdateTime(new Date());
        if (userDetailsMapper.updateByPrimaryKey(details) == 0) {
            return HttpResult.failure(ResultCodeEnum.User_Not_Exists_Exception);
        }
        return HttpResult.success();
    }




}
