package com.program.picture.service;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.CommentGallery;
import com.program.picture.domain.entity.CommentPicture;

public interface CommentService {

    HttpResult insertCommentPicture(CommentPicture record);

    HttpResult insertCommentGallery(CommentGallery record);

    HttpResult deleteCommentPicture(Integer commentId, Integer userId);

    HttpResult deleteCommentGallery(Integer commentId, Integer userId);

    HttpResult selectCommentPicture(Integer pictureId);

    HttpResult selectCommentGallery(Integer galleryId);
}
