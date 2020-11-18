package com.program.picture.service.Impl;


import com.program.picture.common.result.HttpResult;
import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.User;
import com.program.picture.domain.entity.UserDetails;
import com.program.picture.mapper.UserDetailsMapper;
import com.program.picture.mapper.UserMapper;
import com.program.picture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        User newUser = User.builder()
                .name(userName)
                .password(password)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userMapper.insert(newUser);

        users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                UserDetails details = UserDetails.builder()
                        .userId(user.getId())
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build();
                userDetailsMapper.insert(details);
                break;
            }
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult userLogin(String userName, String password) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getPassword().equals(password)) {
                    return HttpResult.success(user.getId());
                } else {
                    return HttpResult.failure(ResultCodeEnum.User_Login_Fail_Exception);
                }
            }
        }
        return HttpResult.failure(ResultCodeEnum.User_Not_Exists_Exception);
    }

    @Override
    public HttpResult userGetIdByName(String userName) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return HttpResult.success(user.getId());
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
        UserDetails oldDetails = userDetailsMapper.selectByPrimaryKey(details.getId());
        if (details.getAge() != null) {
            oldDetails.setAge(details.getAge());
        }
        if (details.getEmail() != null) {
            oldDetails.setEmail(details.getEmail());
        }
        if (details.getGender() != null) {
            oldDetails.setGender(details.getGender());
        }
        if (details.getConstellation() != null) {
            oldDetails.setConstellation(details.getConstellation());
        }
        if (details.getEdu() != null) {
            oldDetails.setEdu(details.getEdu());
        }
        if (details.getJob() != null) {
            oldDetails.setJob(details.getJob());
        }
        if (details.getHobby() != null) {
            oldDetails.setHobby(details.getHobby());
        }
        oldDetails.setUpdateTime(new Date());
        userDetailsMapper.updateByPrimaryKey(oldDetails);
        return HttpResult.success();
    }


}
