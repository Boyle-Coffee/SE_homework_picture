package com.program.picture.service.Impl;

import com.program.picture.common.result.HttpResult;
import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.UserLikeType;
import com.program.picture.mapper.UserLikeTypeMapper;
import com.program.picture.service.LikeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Bluebear
 * @Date: 2020/11/13/20:02
 * @Description:
 */
@Service
public class LikeTypeServiceImpl implements LikeTypeService {

    @Autowired
    private UserLikeTypeMapper userLikeTypeMapper;

    @Override
    public HttpResult AddLikeType(Integer userId, Integer typeId) {
        List<UserLikeType> userLikeTypes = userLikeTypeMapper.selectAll();
        for (UserLikeType userLikeType : userLikeTypes) {
            if (userLikeType.getUserId().equals(userId) && userLikeType.getTypeId().equals(typeId)) {
                return HttpResult.failure(ResultCodeEnum.User_Add_Type_Fail_Exception);
            }
        }
        UserLikeType userLikeType = UserLikeType.builder()
                .userId(userId)
                .typeId(typeId)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userLikeTypeMapper.insert(userLikeType);
        return HttpResult.success();
    }

    @Override
    public HttpResult SelectLikeType(Integer userId) {
        List<Integer> result = new ArrayList<>();
        List<UserLikeType> userLikeTypes = userLikeTypeMapper.selectAll();
        for (UserLikeType userLikeType : userLikeTypes) {
            if (userLikeType.getUserId().equals(userId)) {
                result.add(userLikeType.getTypeId());
            }
        }
        return HttpResult.success(result);
    }

    @Override
    public HttpResult DeleteLikeType(Integer userId, Integer typeId) {
        List<UserLikeType> userLikeTypes = userLikeTypeMapper.selectAll();
        for (UserLikeType userLikeType : userLikeTypes) {
            if (userLikeType.getUserId().equals(userId) && userLikeType.getTypeId().equals(typeId)) {
                userLikeTypeMapper.deleteByPrimaryKey(userLikeType.getId());
                return HttpResult.success();
            }
        }
        return HttpResult.failure(ResultCodeEnum.User_Del_Type_Fail_Exception);
    }
}
