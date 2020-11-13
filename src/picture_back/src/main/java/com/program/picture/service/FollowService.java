package com.program.picture.service;

import com.program.picture.common.result.HttpResult;

/**
 * @Auther: Bluebear
 * @Date: 2020/11/13/19:51
 * @Description:
 */
public interface FollowService {
    HttpResult AddFollow(Integer userId, Integer followId);

    HttpResult DeleteFollow(Integer userId, Integer followId);

    HttpResult SelectWhoFollowMe(Integer userId);

    HttpResult SelectIFollowWho(Integer userId);
}
