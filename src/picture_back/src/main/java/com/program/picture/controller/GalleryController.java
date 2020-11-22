package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.service.CollectionService;
import com.program.picture.service.GalleryService;
import com.program.picture.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 15:14
 **/
@Api(tags = "图库接口")
@RestController
@RequestMapping("/gallery")
public class GalleryController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private CollectionService collectionService;

    @ApiOperation(value = "添加图库", notes = "新增图库")
    @PostMapping("/addGallery")
    public HttpResult addGallery(@RequestBody @Validated Gallery gallery) {
        return galleryService.insert(gallery);
    }

    @ApiOperation(value = "获取图库", notes = "根据图库Id获取图库")
    @GetMapping("/getGallery")
    public HttpResult getGallery(@RequestParam(value = "galleyId") Integer galleyId) {
        return galleryService.selectByPrimaryKey(galleyId);
    }

    @ApiOperation(value = "删除图库", notes = "根据图库Id删除图库")
    @DeleteMapping("/deleteGallery")
    public HttpResult deleteGallery(@RequestParam(value = "galleyId") Integer galleyId,
                                    @RequestParam(value = "userId") Integer userId
    ) {
        return galleryService.deleteByPrimaryKey(galleyId, userId);
    }

    @ApiOperation(value = "更新图库", notes = "更新图库信息")
    @PutMapping("/updateGallery")
    public HttpResult updateGallery(@RequestBody @Validated Gallery gallery) {
        return galleryService.updateByPrimaryKey(gallery);
    }

    @ApiOperation(value = "删除喜欢图库", notes = "根据图库Id和用户Id删除喜欢图库")
    @DeleteMapping("/deleteGalleryLike")
    public HttpResult deleteGalleryLike(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "galleyId") Integer galleyId) {
        return likeService.deleteGalleryLike(userId, galleyId);
    }

    @ApiOperation(value = "添加喜欢图库", notes = "根据图库Id和用户Id删除喜欢图库")
    @PostMapping("/addGalleryLike")
    public HttpResult addGalleryLike(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "galleyId") Integer galleyId) {
        return likeService.addGalleryLike(userId, galleyId);
    }

    @ApiOperation(value = "获取喜欢图库", notes = "根据用户Id获取喜欢图库")
    @GetMapping("/getGalleryLike")
    public HttpResult selectGalleryLike(@RequestParam(value = "userId") Integer userId) {
        return likeService.selectGalleryLike(userId);
    }

    @ApiOperation(value = "删除收藏图库", notes = "根据用户Id获取喜欢图库")
    @DeleteMapping("/deleteGalleryCollection")
    public HttpResult deleteGalleryCollection(@RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "galleyId") Integer galleyId) {
        return collectionService.deleteGalleryCollection(userId, galleyId);
    }

    @ApiOperation(value = "添加收藏图库", notes = "根据用户Id和图库Id添加收藏图库")
    @PostMapping("/addGalleryCollection")
    public HttpResult addGalleryollection(@RequestParam(value = "userId") Integer userId,
                                          @RequestParam(value = "galleyId") Integer galleyId) {
        return collectionService.insertGalleryCollection(userId, galleyId);
    }

    @ApiOperation(value = "获取收藏图库", notes = "根据用户Id获取收藏图库")
    @GetMapping("/getGalleryCollection")
    public HttpResult selectGalleryCollection(@RequestParam(value = "userId") Integer userId) {
        return collectionService.selectGalleryCollection(userId);
    }

    @ApiOperation(value = "删除图库图片", notes = "根据图片Id和图库Id删除图库中的图片")
    @DeleteMapping("/deleteGalleryPicture")
    public HttpResult deleteGalleryPicture(@RequestParam(value = "galleryId") Integer galleryId,
                                           @RequestParam(value = "pictureId") Integer pictureId,
                                           @RequestParam(value = "userId") Integer userId) {
        return galleryService.deleteGalleryPicture(galleryId, pictureId, userId);
    }

    @ApiOperation(value = "添加图库图片", notes = "根据图片Id和图库Id添加图库中的图片")
    @PostMapping("/addGalleryPicture")
    public HttpResult addGalleryPicture(@RequestParam(value = "galleryId") Integer galleryId,
                                        @RequestParam(value = "pictureId") Integer pictureId) {
        return galleryService.insertGalleryPicture(galleryId, pictureId);
    }

    @ApiOperation(value = "获取用户图库", notes = "根据用户Id获取用户图库")
    @GetMapping("/getUserGallery")
    public HttpResult selectUserGallery(@RequestParam(value = "userId") Integer userId) {
        return galleryService.selectGalleryByUserId(userId);
    }

    @ApiOperation(value = "获取图库中的图片", notes = "根据图库Id获取图库图片")
    @GetMapping("/getGalleryPicture")
    public HttpResult selectGalleryPicture(@RequestParam(value = "galleryId") Integer galleryId) {
        return galleryService.selectPictureByGallery(galleryId);
    }
}
