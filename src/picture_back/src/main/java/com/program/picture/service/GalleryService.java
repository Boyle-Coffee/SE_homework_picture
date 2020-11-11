package com.program.picture.service;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Gallery;

public interface GalleryService {

    HttpResult deleteByPrimaryKey(Integer id);

    HttpResult insert(Gallery record);

    HttpResult selectByPrimaryKey(Integer id);

    HttpResult selectAll();

    HttpResult updateByPrimaryKey(Gallery record);
}
