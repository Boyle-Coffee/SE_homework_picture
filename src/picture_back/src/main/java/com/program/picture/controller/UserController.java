package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.UserDetails;
import com.program.picture.service.FollowService;
import com.program.picture.service.LikeTypeService;
import com.program.picture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Blue bear
 * @Date: 2020/11/11/23:41
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Autowired
    private LikeTypeService likeTypeService;


    //test
    @PostMapping("/login")
    public HttpResult userLogin(
            @RequestParam(value = "userName") String userName
            , @RequestParam(value = "password") String password) {
        return userService.userLogin(userName, password);
    }

    @PostMapping("/register")
    public HttpResult userRegister(
            @RequestParam(value = "userName") String userName
            , @RequestParam(value = "password") String password) {
        return userService.userRegister(userName, password);
    }

    @PutMapping("/updatePassword")
    public HttpResult userUpdatePassword(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword) {
        return userService.userUpdatePassword(userId, oldPassword, oldPassword);
    }

    @PutMapping("/updateUserName")
    public HttpResult userUpdateUserName(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "userName") String userName) {
        return userService.userUpdateUserName(userId, userName);
    }

    @PutMapping("/udateDetails")
    public HttpResult userUpdateDetails(
            @RequestParam(value = "userId") UserDetails details) {
        return userService.userUpdateDetails(details);
    }

    @GetMapping("/getDetails")
    public HttpResult selectDetails(@RequestParam(value = "userId") Integer userId) {
        return userService.userSelectDetails(userId);
    }


    @PostMapping("/addFollow")
    public HttpResult addFollow(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "followId") Integer followId) {
        return followService.AddFollow(userId, followId);
    }

    @DeleteMapping("/deleteFollow")
    public HttpResult deleteFollow(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "followId") Integer followId) {
        return followService.DeleteFollow(userId, followId);
    }

    @GetMapping("/getIFollowWho")
    public HttpResult selectIFollowWho(
            @RequestParam(value = "userId") Integer userId) {
        return followService.SelectIFollowWho(userId);
    }

    @GetMapping("/getWhoFollowMe")
    public HttpResult selectWhoFollowMe(
            @RequestParam(value = "userId") Integer userId) {
        return followService.SelectWhoFollowMe(userId);
    }

    @PostMapping("/addLikeType")
    public HttpResult addLikeType(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "typeId") Integer typeId) {
        return likeTypeService.AddLikeType(userId, typeId);
    }

    @DeleteMapping("/deleteLikeType")
    public HttpResult deleteLikeType(
            @RequestParam(value = "userId") Integer userId
            , @RequestParam(value = "followId") Integer typeId) {
        return likeTypeService.DeleteLikeType(userId, typeId);
    }

    @GetMapping("/selectLikeType")
    public HttpResult selectLikeType(
            @RequestParam(value = "userId") Integer userId) {
        return likeTypeService.SelectLikeType(userId);
    }
}
