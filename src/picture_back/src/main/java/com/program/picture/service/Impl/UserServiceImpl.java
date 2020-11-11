package com.program.picture.service.Impl;


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

import java.util.AbstractList;
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

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Autowired
    private UserLikeTypeMapper userLikeTypeMapper;

    @Override
    public ResultCodeEnum userRegister(String userName, String password) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                return ResultCodeEnum.User_Exists_Exception;
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

        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public ResultCodeEnum userLogin(String userName, String password) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getPassword().equals(password)) {
                    return ResultCodeEnum.SUCCESS;
                } else {
                    return ResultCodeEnum.User_Login_Fail;
                }
            }
        }
        return ResultCodeEnum.User_Not_Exists_Exception;
    }

    @Override
    public ResultCodeEnum userChangePassword(String userName, String oldPassword, String newPassword) {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                if (user.getPassword().equals(oldPassword)) {
                    user.setPassword(newPassword);
                    user.setUpdateTime(new Date());
                    userMapper.updateByPrimaryKey(user);
                    return ResultCodeEnum.SUCCESS;
                } else {
                    return ResultCodeEnum.User_Change_Password_Fail;
                }
            }
        }
        return ResultCodeEnum.User_Not_Exists_Exception;
    }

    @Override
    public UserDetails userGetDetails(Integer userId) {
        List<UserDetails> userDetails = userDetailsMapper.selectAll();
        for (UserDetails userDetail : userDetails) {
            if (userDetail.getUserId().equals(userId)) {
                return userDetail;
            }
        }
        return null;
    }

    @Override
    public ResultCodeEnum userChangeDetails(UserDetails details) {
        details.setUpdateTime(new Date());
        int success = userDetailsMapper.updateByPrimaryKey(details);
        if (success == 0) return ResultCodeEnum.User_Not_Exists_Exception;
        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public ResultCodeEnum userFollow(Integer userId, Integer followId) {

        List<UserFollow> userFollows = userFollowMapper.selectAll();
        for (UserFollow userFollow : userFollows) {
            if (userFollow.getUserId().equals(userId) && userFollow.getFollowId().equals(followId)) {
                return ResultCodeEnum.User_Follow_Fail;
            }
        }

        UserFollow userFollow = UserFollow.builder()
                .userId(userId)
                .followId(followId)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userFollowMapper.insert(userFollow);
        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public ResultCodeEnum userFollowCancel(Integer userId, Integer followId) {

        List<UserFollow> userFollows = userFollowMapper.selectAll();
        for (UserFollow userFollow : userFollows) {
            if (userFollow.getUserId().equals(userId) && userFollow.getFollowId().equals(followId)) {
                userFollowMapper.deleteByPrimaryKey(userFollow.getId());
                return ResultCodeEnum.SUCCESS;
            }
        }
        return ResultCodeEnum.User_Follow_Cancel_Fail;
    }

    @Override
    public ResultCodeEnum userAddLikeType(Integer userId, Integer typeId) {
        List<UserLikeType> userLikeTypes = userLikeTypeMapper.selectAll();
        for (UserLikeType userLikeType : userLikeTypes) {
            if (userLikeType.getUserId().equals(userId) && userLikeType.getTypeId().equals(typeId)) {
                return ResultCodeEnum.User_Add_Type_Fail;
            }
        }
        UserLikeType userLikeType = UserLikeType.builder()
                .userId(userId)
                .typeId(typeId)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userLikeTypeMapper.insert(userLikeType);
        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public List<Integer> userGetLikeType(Integer userId) {
        List<Integer> result = new ArrayList<>();
        List<UserLikeType> userLikeTypes = userLikeTypeMapper.selectAll();
        for (UserLikeType userLikeType : userLikeTypes) {
            if (userLikeType.getUserId().equals(userId)) {
                result.add(userLikeType.getTypeId());
            }
        }
        return result;
    }

    @Override
    public ResultCodeEnum userDeleteLikeType(Integer userId, Integer typeId) {
        List<UserLikeType> userLikeTypes = userLikeTypeMapper.selectAll();
        for (UserLikeType userLikeType : userLikeTypes) {
            if (userLikeType.getUserId().equals(userId) && userLikeType.getTypeId().equals(typeId)) {
                userLikeTypeMapper.deleteByPrimaryKey(userLikeType.getId());
                return ResultCodeEnum.SUCCESS;
            }
        }
        return ResultCodeEnum.User_Del_Type_Fail;
    }

}
