package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;
import com.program.picture.service.PictureService;
import com.program.picture.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * */

    @Autowired
    private PictureService pictureService;

    @Autowired
    private TypeService typeService;

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

    @PostMapping("/addPicture")
    public HttpResult addPictureType(@RequestParam(value = "typeId") Integer typeId,
                                     @RequestParam(value = "pictureId") Integer pictureId) {
        return typeService.insertPictureType(typeId, pictureId);
    }
}
