package com.program.picture.service;


import com.program.picture.common.result.HttpResult;

public interface LikeService {

    HttpResult selectPictureLike(Integer userId);

    HttpResult deletePictureLike(Integer userId, Integer pictureId);

    HttpResult addPictureLike(Integer userId, Integer pictureId);

    HttpResult selectGalleyLike(Integer userId);

    HttpResult deleteGalleyLike(Integer userId, Integer galleyId);

    HttpResult addGalleyLike(Integer userId, Integer galleyId);
}
