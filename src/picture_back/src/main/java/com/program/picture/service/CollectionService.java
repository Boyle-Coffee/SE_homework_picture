package com.program.picture.service;

import com.program.picture.common.result.HttpResult;

public interface CollectionService {

    HttpResult insertPictureCollection(Integer userId, Integer pictureId);

    HttpResult delectPictureCollection(Integer userId, Integer pictureId);

    HttpResult selectPictureCollection(Integer userId);
}
