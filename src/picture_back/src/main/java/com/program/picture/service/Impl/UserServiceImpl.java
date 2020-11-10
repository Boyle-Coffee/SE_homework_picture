package com.program.picture.service.Impl;


import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.User;
import com.program.picture.domain.entity.UserDetails;
import com.program.picture.domain.entity.UserFollow;
import com.program.picture.mapper.UserDetailsMapper;
import com.program.picture.mapper.UserFollowMapper;
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

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    public ResultCodeEnum userRegister(User user) {
        List<User> users = userMapper.selectAll();
        for (User it : users) {
            if (it.getName().equals(user.getName())) {
                return ResultCodeEnum.User_Exists_Exception;
            }
        }
        user.setCreateTime(new Date());
        userMapper.insert(user);

        UserDetails details = new UserDetails();
        details.setCreateTime(new Date());
        userDetailsMapper.insert(details);

        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public ResultCodeEnum userLogin(User user) {
        List<User> users = userMapper.selectAll();
        for (User it : users) {
            if (it.getName().equals(user.getName())) {
                if (it.getPassword().equals(user.getPassword())) {
                    return ResultCodeEnum.SUCCESS;
                } else {
                    return ResultCodeEnum.User_Login_Fail;
                }
            }
        }
        return ResultCodeEnum.User_Not_Exists_Exception;
    }


    @Override
    public ResultCodeEnum userChangeDetails(UserDetails details) {
        details.setUpdateTime(new Date());
        int success = userDetailsMapper.updateByPrimaryKey(details);
        if (success == 0) return ResultCodeEnum.User_Not_Exists_Exception;
        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public UserDetails userGetDetails(User user) {
        List<User> users = userMapper.selectAll();
        for (User it : users) {
            if (it.getName().equals(user.getName())) {
                List<UserDetails> userDetails = userDetailsMapper.selectAll();
                for (UserDetails userDetail : userDetails) {
                    if (userDetail.getUserId().equals(user.getId())) {
                        return userDetail;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ResultCodeEnum userFollow(User from, User to) {
        List<UserFollow> userFollows = userFollowMapper.selectAll();
        for (UserFollow userFollow : userFollows) {
            if (userFollow.getUserId().equals(from.getId()) && userFollow.getFollowId().equals(to.getId())) {
                return ResultCodeEnum.User_Follow_Fail;
            }
        }
        UserFollow userFollow = new UserFollow();
        userFollow.setCreateTime(new Date());
        userFollow.setUpdateTime(new Date());
        userFollow.setUserId(from.getId());
        userFollow.setFollowId(to.getId());

        userFollowMapper.insert(userFollow);
        return ResultCodeEnum.SUCCESS;
    }

    @Override
    public ResultCodeEnum userFollowCancel(User from, User to) {
        List<UserFollow> userFollows = userFollowMapper.selectAll();
        for (UserFollow userFollow : userFollows) {
            if (userFollow.getUserId().equals(from.getId()) && userFollow.getFollowId().equals(to.getId())) {
                userFollowMapper.deleteByPrimaryKey(userFollow.getId());
                return ResultCodeEnum.SUCCESS;
            }
        }
        return ResultCodeEnum.User_Follow_Cancel_Fail;
    }


}
