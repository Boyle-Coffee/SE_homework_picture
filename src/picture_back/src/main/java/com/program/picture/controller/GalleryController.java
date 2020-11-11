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
    private CollectionService conllectionService;

    @PostMapping("/addGalley")
    public HttpResult addGalley(@RequestBody @Validated Gallery gallery) {
        return galleryService.insert(gallery);
    }

    @GetMapping("/getGalley")
    public HttpResult selectGalley(@RequestParam(value = "galleyId") Integer galleyId) {
        return galleryService.selectByPrimaryKey(galleyId);
    }

    @DeleteMapping("/deleteGalley")
    public HttpResult deleteGalley(@RequestParam(value = "galleyId") Integer galleyId) {
        return galleryService.deleteByPrimaryKey(galleyId);
    }

    @PutMapping("/updateGalley")
    public HttpResult updateGalley(@RequestBody @Validated Gallery gallery) {
        return galleryService.updateByPrimaryKey(gallery);
    }



}
