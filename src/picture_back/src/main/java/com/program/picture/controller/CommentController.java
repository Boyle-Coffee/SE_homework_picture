package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.CommentGallery;
import com.program.picture.domain.entity.CommentPicture;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-12 15:54
 **/
@Api(tags = "评论接口")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取图库评论", notes = "根据图库Id获取图库评论")
    @GetMapping("/getCommentGallery")
    public HttpResult selectCommentGallery(@RequestParam(value = "galleryId") Integer galleryId) {
        return commentService.selectCommentGallery(galleryId);
    }

    @ApiOperation(value = "获取图片评论", notes = "根据图片Id获取图片评论")
    @GetMapping("/getCommentPicture")
    public HttpResult selectCommentPicture(@RequestParam(value = "pictureId") Integer pictureId) {
        return commentService.selectCommentPicture(pictureId);
    }

    @ApiOperation(value = "添加图库评论", notes = "新增图库评论")
    @PostMapping("/addCommentGallery")
    public HttpResult addCommentGallery(@RequestBody @Validated CommentGallery commentGallery) {
        return commentService.insertCommentGallery(commentGallery);
    }

    @ApiOperation(value = "添加图片评论", notes = "新增图片评论")
    @PostMapping("/addCommentPicture")
    public HttpResult addCommentPicture(@RequestBody @Validated CommentPicture commentPicture) {
        return commentService.insertCommentPicture(commentPicture);
    }

    @ApiOperation(value = "删除图库评论", notes = "根据用户Id以及评论Id删除评论")
    @DeleteMapping("/deleteCommentGallery")
    public HttpResult deleteCommentGallery(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "commentId") Integer commentId) {
        return commentService.deleteCommentGallery(commentId, userId);
    }

    @ApiOperation(value = "删除图片评论", notes = "根据用户Id以及评论Id删除评论")
    @DeleteMapping("/deleteCommentPicture")
    public HttpResult deleteCommentPicture(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "commentId") Integer commentId) {
        return commentService.deleteCommentPicture(commentId, userId);
    }
}
