package com.program.picture.service.Impl;

import com.program.picture.common.result.HttpResult;
import com.program.picture.common.result.ResultCodeEnum;
import com.program.picture.domain.entity.UserFollow;
import com.program.picture.mapper.UserFollowMapper;
import com.program.picture.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Bluebear
 * @Date: 2020/11/13/19:52
 * @Description:
 */
@Service
public class FollowServiceImpl  implements FollowService {
    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    public HttpResult AddFollow(Integer userId, Integer followId) {

        List<UserFollow> userFollows = userFollowMapper.selectAll();
        for (UserFollow userFollow : userFollows) {
            if (userFollow.getUserId().equals(userId) && userFollow.getFollowId().equals(followId)) {
                return HttpResult.failure(ResultCodeEnum.User_Follow_Fail_Exception);
            }
        }

        UserFollow userFollow = UserFollow.builder()
                .userId(userId)
                .followId(followId)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        userFollowMapper.insert(userFollow);
        return HttpResult.success();
    }

    @Override
    public HttpResult DeleteFollow(Integer userId, Integer followId) {

        List<UserFollow> userFollows = userFollowMapper.selectAll();
        for (UserFollow userFollow : userFollows) {
            if (userFollow.getUserId().equals(userId) && userFollow.getFollowId().equals(followId)) {
                userFollowMapper.deleteByPrimaryKey(userFollow.getId());
                return HttpResult.success();
            }
        }
        return HttpResult.failure(ResultCodeEnum.User_Delete_Follow_Fail_Exception);
    }

    @Override
    public HttpResult SelectWhoFollowMe(Integer userId) {
        List<UserFollow> userFollows = userFollowMapper.selectAll();
        List<Integer> result = new ArrayList<>();
        for (UserFollow userFollow : userFollows) {
            if(userFollow.getFollowId()==userId){
                result.add(userFollow.getUserId());
            }
        }
        return HttpResult.success(result);
    }

    @Override
    public HttpResult SelectIFollowWho(Integer userId) {
        List<UserFollow> userFollows = userFollowMapper.selectAll();
        List<Integer> result = new ArrayList<>();
        for (UserFollow userFollow : userFollows) {
            if(userFollow.getUserId()==userId){
                result.add(userFollow.getFollowId());
            }
        }
        return HttpResult.success(result);
    }
}
