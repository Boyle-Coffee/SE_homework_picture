package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.service.CollectionService;
import com.program.picture.service.GalleryService;
import com.program.picture.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 15:14
 **/
@RestController
@RequestMapping("/gallery")
public class GalleryController {
    /*
     * todo
     *  图库的查找（已完成）
     *  图库的添加（已完成）
     *  图库的删除（已完成）
     *  图库的更新（已完成）
     *
     * todo
     *  添加图片进入图库
     *  删除图库中的图片
     *
     * todo
     *  添加图库收藏
     *  删除图库收藏
     *
     * todo
     *  添加图库喜欢
     *  删除图库喜欢
     * */

    @Autowired
    private LikeService likeService;

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private CollectionService collectionService;

    @PostMapping("/addGallery")
    public HttpResult addGallery(@RequestBody @Validated Gallery gallery) {
        return galleryService.insert(gallery);
    }

    @GetMapping("/getGallery")
    public HttpResult getGallery(@RequestParam(value = "galleyId") Integer galleyId) {
        return galleryService.selectByPrimaryKey(galleyId);
    }

    @DeleteMapping("/deleteGallery")
    public HttpResult deleteGallery(@RequestParam(value = "galleyId") Integer galleyId) {
        return galleryService.deleteByPrimaryKey(galleyId);
    }

    @PutMapping("/updateGallery")
    public HttpResult updateGallery(@RequestBody @Validated Gallery gallery) {
        return galleryService.updateByPrimaryKey(gallery);
    }

    @DeleteMapping("/deleteGalleryLike")
    public HttpResult deleteGalleryLike(@RequestParam(value = "userId") Integer userId,
                                        @RequestParam(value = "pictureId") Integer pictureId) {
        return likeService.deleteGalleryLike(userId, pictureId);
    }

    @PostMapping("/addGalleryLike")
    public HttpResult addGalleryLike(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "pictureId") Integer pictureId) {
        return likeService.addGalleryLike(userId, pictureId);
    }

    @GetMapping("/getGalleryLike")
    public HttpResult selectGalleryLike(@RequestParam(value = "userId") Integer userId) {
        return likeService.selectGalleryLike(userId);
    }

    @DeleteMapping("/deleteGalleryCollection")
    public HttpResult deleteGalleryCollection(@RequestParam(value = "userId") Integer userId,
                                              @RequestParam(value = "pictureId") Integer pictureId) {
        return collectionService.deleteGalleryCollection(userId, pictureId);
    }

    @PostMapping("/addGalleryCollection")
    public HttpResult addGalleryollection(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "pictureId") Integer pictureId) {
        return collectionService.insertGalleryCollection(userId, pictureId);
    }

    @GetMapping("/getGalleryCollection")
    public HttpResult selectGalleryCollection(@RequestParam(value = "userId") Integer userId) {
        return collectionService.selectGalleryCollection(userId);
    }
}
