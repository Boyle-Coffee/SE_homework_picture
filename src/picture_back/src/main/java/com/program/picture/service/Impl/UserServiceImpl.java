package com.program.picture.service.Impl;



import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.User;
import com.program.picture.mapper.UserMapper;
import com.program.picture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Bluebear
 * @date: 2020/11/09/19:02
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultCodeEnum userRegister(User user) {
        List<User> users = userMapper.selectAll();
        for(User it:users){
            if(it.getName().equals(user.getName())) {
                return ResultCodeEnum.User_Exists_Exception;
            }
        }
        userMapper.insert(user);
        return ResultCodeEnum.SUCCESS;
    }
}
