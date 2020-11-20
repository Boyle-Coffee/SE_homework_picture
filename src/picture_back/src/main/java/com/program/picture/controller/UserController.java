package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.UserDetails;
import com.program.picture.service.FollowService;
import com.program.picture.service.LikeTypeService;
import com.program.picture.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Blue bear
 * @Date: 2020/11/11/23:41
 * @Description:
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Autowired
    private LikeTypeService likeTypeService;


    @ApiOperation(value = "用户登录", notes = "根据用户名称以及用户密码进行登录")
    @PostMapping("/login")
    public HttpResult userLogin(
            @RequestParam(value = "userName") String userName
            , @RequestParam(value = "password") String password) {
        return userService.userLogin(userName, password);
    }

    @ApiOperation(value = "获取用户Id", notes = "根据用户名称获取Id")
    @GetMapping("/getIdByName")
    public HttpResult userGetIdByName(
            @RequestParam(value = "userName") String userName) {
        return userService.userGetIdByName(userName);
    }

    @ApiOperation(value = "用户注册", notes = "根据用户名称以及用户密码进行注册")
    @PostMapping("/register")
    public HttpResult userRegister(
            @RequestParam(value = "userName") String userName
            , @RequestParam(value = "password") String password) {
        return userService.userRegister(userName, password);
    }

    @ApiOperation(value = "更新密码", notes = "根据用户Id以及旧密码和新密码进行密码更新")
    @PutMapping("/updatePassword")
    public HttpResult userUpdatePassword(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword) {
        return userService.userUpdatePassword(userId, oldPassword, oldPassword);
    }

    @ApiOperation(value = "更新用户名称", notes = "根据用户Id以及用户名称进行名称更新")
    @PutMapping("/updateUserName")
    public HttpResult userUpdateUserName(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "userName") String userName) {
        return userService.userUpdateUserName(userId, userName);
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @PutMapping("/updateDetails")
    public HttpResult userUpdateDetails(
            @RequestBody @Validated UserDetails details) {
        return userService.userUpdateDetails(details);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户Id获取用户信息")
    @GetMapping("/getDetails")
    public HttpResult selectDetails(@RequestParam(value = "userId") Integer userId) {
        return userService.userSelectDetails(userId);
    }

    @ApiOperation(value = "添加用户关注", notes = "根据用户Id以及关注用户id添加用户关注")
    @PostMapping("/addFollow")
    public HttpResult addFollow(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "followId") Integer followId) {
        return followService.AddFollow(userId, followId);
    }

    @ApiOperation(value = "删除用户关注", notes = "根据用户Id以及关注用户Id删除用户关注")
    @DeleteMapping("/deleteFollow")
    public HttpResult deleteFollow(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "followId") Integer followId) {
        return followService.DeleteFollow(userId, followId);
    }

    @ApiOperation(value = "获取用户关注", notes = "根据用户Id获取该用户的关注名单")
    @GetMapping("/getIFollowWho")
    public HttpResult selectIFollowWho(
            @RequestParam(value = "userId") Integer userId) {
        return followService.SelectIFollowWho(userId);
    }

    @ApiOperation(value = "获取用户被关注", notes = "根据用户Id获取该用户的被关注名单")
    @GetMapping("/getWhoFollowMe")
    public HttpResult selectWhoFollowMe(
            @RequestParam(value = "userId") Integer userId) {
        return followService.SelectWhoFollowMe(userId);
    }

    @ApiOperation(value = "获取喜欢标签", notes = "根据用户Id和标签Id新增用户喜欢标签")
    @PostMapping("/addLikeType")
    public HttpResult addLikeType(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "typeId") Integer typeId) {
        return likeTypeService.AddLikeType(userId, typeId);
    }

    @ApiOperation(value = "删除喜欢标签", notes = "根据用户Id和标签Id删除用户喜欢标签")
    @DeleteMapping("/deleteLikeType")
    public HttpResult deleteLikeType(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "followId") Integer typeId) {
        return likeTypeService.DeleteLikeType(userId, typeId);
    }

    @ApiOperation(value = "获取喜欢标签", notes = "根据用户Id获取用户喜欢标签")
    @GetMapping("/selectLikeType")
    public HttpResult selectLikeType(
            @RequestParam(value = "userId") Integer userId) {
        return likeTypeService.SelectLikeType(userId);
    }
}
