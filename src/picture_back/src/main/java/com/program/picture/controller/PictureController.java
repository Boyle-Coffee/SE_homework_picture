package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;
import com.program.picture.service.CollectionService;
import com.program.picture.service.LikeService;
import com.program.picture.service.PictureService;
import com.program.picture.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-07 20:18
 **/
@RestController
@RequestMapping("/picture")
public class PictureController {
    /*
     * todo
     *   图片的查找—根据以图搜图查看相似图片
     *   图片的添加(已初步完成)
     *       图片的云端添加
     *   图片的删除（已完成）
     *   图片的详情更新（已完成）
     *
     * todo
     *   图片标签的查询（已完成）
     *   图片标签的添加(已完成)
     *   图片标签的删除（已完成）
     *
     * todo
     *   查看用户收藏的图片（已完成）
     *   添加图片进入我的收藏（已完成）
     *   删除我的收藏的图片（已完成）
     *
     * todo
     *   查看用户喜欢的图片（已完成）
     *   添加图片进入我的喜欢（已完成）
     *   删除我的喜欢的图片（已完成）
     *
     * */

    @Autowired
    private LikeService likeService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private CollectionService collectionService;

    @PostMapping("/addPicture")
    public HttpResult addPicture(@RequestBody @Validated Picture picture) {
        return pictureService.insert(picture);
    }

    @DeleteMapping("/deletePicture")
    public HttpResult deletePicture(@RequestParam(value = "pictureId") Integer pictureId) {
        return pictureService.deleteByPrimaryKey(pictureId);
    }

    @PutMapping("/updatePicture")
    public HttpResult updatePicture(@RequestBody @Validated Picture picture) {
        return pictureService.updateByPrimaryKey(picture);
    }

    @GetMapping("/getPicture")
    public HttpResult selectPicture(@RequestParam(value = "pictureId") Integer pictureId) {
        return pictureService.selectByPrimaryKey(pictureId);
    }

    @GetMapping("/getPictureType")
    public HttpResult selectPictureType(@RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.selectPictureType(pictureId);
    }

    @DeleteMapping("/deletePictureType")
    public HttpResult deletePictureType(@RequestParam(value = "typeId") Integer typeId,
                                        @RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.deletePictureType(typeId, pictureId);
    }

    @PostMapping("/addPictureType")
    public HttpResult addPictureType(@RequestParam(value = "typeId") Integer typeId,
                                     @RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.insertPictureType(typeId, pictureId);
    }

    @DeleteMapping("/deletePictureCollection")
    public HttpResult deletePictureCollection(@RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "pictureId") Integer pictureId) {
        return collectionService.deletePictureCollection(userId, pictureId);
    }

    @PostMapping("/addPictureCollection")
    public HttpResult addPictureCollection(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "pictureId") Integer pictureId) {
        return collectionService.insertPictureCollection(userId, pictureId);
    }

    @GetMapping("/getPictureCollection")
    public HttpResult selectPictureCollection(@RequestParam(value = "userId") Integer userId) {
        return collectionService.selectPictureCollection(userId);
    }

    @DeleteMapping("/deletePictureLike")
    public HttpResult deletePictureLike(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "pictureId") Integer pictureId) {
        return likeService.deletePictureLike(userId, pictureId);
    }

    @PostMapping("/addPictureLike")
    public HttpResult addPictureLike(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "pictureId") Integer pictureId) {
        return likeService.addPictureLike(userId, pictureId);
    }

    @GetMapping("/getPictureLike")
    public HttpResult selectPictureLike(@RequestParam(value = "userId") Integer userId) {
        return likeService.selectPictureLike(userId);
    }

}
