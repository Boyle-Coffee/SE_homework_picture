package com.program.picture.service;


import com.program.picture.common.result.HttpResult;

public interface LikeService {

    HttpResult selectPictureLike(Integer userId);

    HttpResult deletePictureLike(Integer userId, Integer pictureId);

    HttpResult addPictureLike(Integer userId, Integer pictureId);

    HttpResult selectGalleryLike(Integer userId);

    HttpResult deleteGalleryLike(Integer userId, Integer galleyId);

    HttpResult addGalleryLike(Integer userId, Integer galleyId);
}
