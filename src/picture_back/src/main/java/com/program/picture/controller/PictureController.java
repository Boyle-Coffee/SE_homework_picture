package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.common.util.COSClientUtil;
import com.program.picture.domain.entity.Picture;
import com.program.picture.service.CollectionService;
import com.program.picture.service.LikeService;
import com.program.picture.service.PictureService;
import com.program.picture.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-07 20:18
 **/
@Api(tags = "图片接口")
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private COSClientUtil cosClientUtil;

    @Autowired
    private LikeService likeService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private CollectionService collectionService;

    @ApiOperation(value = "获取图片Url", notes = "上传图片至腾讯云，获取图片url——用于添加图片")
    @PostMapping("/getPictureUrl")
    public HttpResult getPictureUrl(@RequestParam(value = "file") MultipartFile file) {
        return HttpResult.success(cosClientUtil.upload(file, "pictureWork/"));
    }

    @ApiOperation(value = "添加图片", notes = "新增图片")
    @PostMapping("/addPicture")
    public HttpResult addPicture(@RequestBody @Validated Picture picture) {
        return pictureService.insert(picture);
    }

    @ApiOperation(value = "删除图片", notes = "根据图片Id删除图片")
    @DeleteMapping("/deletePicture")
    public HttpResult deletePicture(@RequestParam(value = "pictureId") Integer pictureId,
                                    @RequestParam(value = "userId") Integer userId) {
        return pictureService.deleteByPrimaryKey(pictureId, userId);
    }

    @ApiOperation(value = "更新图片", notes = "更新图片信息")
    @PutMapping("/updatePicture")
    public HttpResult updatePicture(@RequestBody @Validated Picture picture) {
        return pictureService.updateByPrimaryKey(picture);
    }

    @ApiOperation(value = "获取图片", notes = "根据图片Id获取图片")
    @GetMapping("/getPicture")
    public HttpResult selectPicture(@RequestParam(value = "pictureId") Integer pictureId) {
        return pictureService.selectByPrimaryKey(pictureId);
    }

    @ApiOperation(value = "获取图片标签", notes = "根据图片Id获取图片标签")
    @GetMapping("/getPictureType")
    public HttpResult selectPictureType(@RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.selectPictureType(pictureId);
    }

    @ApiOperation(value = "删除图片标签", notes = "根据图片Id和标签Id删除图片标签")
    @DeleteMapping("/deletePictureType")
    public HttpResult deletePictureType(@RequestParam(value = "typeId") Integer typeId,
                                        @RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.deletePictureType(typeId, pictureId);
    }

    @ApiOperation(value = "添加图片标签", notes = "根据图片Id和标签Id新增图片标签")
    @PostMapping("/addPictureType")
    public HttpResult addPictureType(@RequestParam(value = "typeId") Integer typeId,
                                     @RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.insertPictureType(typeId, pictureId);
    }

    @ApiOperation(value = "删除收藏图片", notes = "根据图片Id和用户Id删除收藏图片")
    @DeleteMapping("/deletePictureCollection")
    public HttpResult deletePictureCollection(@RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "pictureId") Integer pictureId) {
        return collectionService.deletePictureCollection(userId, pictureId);
    }

    @ApiOperation(value = "添加收藏图片", notes = "根据图片Id和用户Id新增图片")
    @PostMapping("/addPictureCollection")
    public HttpResult addPictureCollection(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "pictureId") Integer pictureId) {
        return collectionService.insertPictureCollection(userId, pictureId);
    }

    @ApiOperation(value = "获取收藏图片", notes = "根据用户Id获取收藏图片")
    @GetMapping("/getPictureCollection")
    public HttpResult selectPictureCollection(@RequestParam(value = "userId") Integer userId) {
        return collectionService.selectPictureCollection(userId);
    }

    @ApiOperation(value = "删除喜欢图片", notes = "根据图片Id和用户Id删除喜欢图片")
    @DeleteMapping("/deletePictureLike")
    public HttpResult deletePictureLike(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "pictureId") Integer pictureId) {
        return likeService.deletePictureLike(userId, pictureId);
    }

    @ApiOperation(value = "添加喜欢图片", notes = "根据图片Id和用户Id新增喜欢图片")
    @PostMapping("/addPictureLike")
    public HttpResult addPictureLike(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "pictureId") Integer pictureId) {
        return likeService.addPictureLike(userId, pictureId);
    }

    @ApiOperation(value = "获取喜欢图片", notes = "根据用户Id获取喜欢图片")
    @GetMapping("/getPictureLike")
    public HttpResult selectPictureLike(@RequestParam(value = "userId") Integer userId) {
        return likeService.selectPictureLike(userId);
    }

    @ApiOperation(value = "获取标签类图片", notes = "根据标签id获取该标签类图片")
    @GetMapping("/getTypePicture")
    public HttpResult selectTypePicture(@RequestParam(value = "typeId") Integer typeId) {
        return pictureService.selectPictureByType(typeId);
    }

    @ApiOperation(value = "获取用户图片", notes = "根据用户id获取该用户图片")
    @GetMapping("/getUserPicture")
    public HttpResult selectUserPicture(@RequestParam(value = "userId") Integer userId) {
        return pictureService.selectPictureByUserId(userId);
    }

    @ApiOperation(value = "获取相似图片", notes = "根据图片url获取该图片的相似图片")
    @PostMapping("/getSimilarPicture")
    public HttpResult selectSimilarPicture(@RequestParam(value = "pictureUrl") String pictureUrl) {
        return pictureService.selectSimilarPicture(pictureUrl);
    }

    @ApiOperation(value = "获取所有图片", notes = "根据时间进行排序获取所有公开图片(1为升序，0为降序)")
    @PostMapping("/getAllPictureOrderTime")
    public HttpResult sleectAllPictureOrderTime(@RequestParam(value = "isAsc") Integer isAsc) {
        return pictureService.selectAll(isAsc);
    }
}
