package com.program.picture.controller;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.CommentGallery;
import com.program.picture.domain.entity.CommentPicture;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-12 15:54
 **/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/getCommentGallery")
    public HttpResult selectCommentGallery(@RequestParam(value = "galleryId") Integer galleryId) {
        return commentService.selectCommentGallery(galleryId);
    }

    @GetMapping("/getCommentPicture")
    public HttpResult selectCommentPicture(@RequestParam(value = "pictureId") Integer pictureId) {
        return commentService.selectCommentPicture(pictureId);
    }

    @PostMapping("/addCommentGallery")
    public HttpResult addCommentGallery(@RequestBody @Validated CommentGallery commentGallery) {
        return commentService.insertCommentGallery(commentGallery);
    }

    @PostMapping("/addCommentPicture")
    public HttpResult addCommentPicture(@RequestBody @Validated CommentPicture commentPicture) {
        return commentService.insertCommentPicture(commentPicture);
    }

    @DeleteMapping("/deleteCommentGallery")
    public HttpResult deleteCommentGallery(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "commentId") Integer commentId) {
        return commentService.deleteCommentGallery(commentId, userId);
    }

    @DeleteMapping("/deleteCommentPicture")
    public HttpResult deleteCommentPicture(@RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "commentId") Integer commentId) {
        return commentService.deleteCommentPicture(commentId, userId);
    }
}
