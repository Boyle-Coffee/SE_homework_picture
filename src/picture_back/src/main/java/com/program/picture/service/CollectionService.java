package com.program.picture.service;

import com.program.picture.common.result.HttpResult;

public interface CollectionService {

    HttpResult insertPictureCollection(Integer userId, Integer pictureId);

    HttpResult deletePictureCollection(Integer userId, Integer pictureId);

    HttpResult selectPictureCollection(Integer userId);

    HttpResult insertGalleryCollection(Integer userId, Integer galleryId);

    HttpResult deleteGalleryCollection(Integer userId, Integer galleryId);

    HttpResult selectGalleryCollection(Integer userId);
}
