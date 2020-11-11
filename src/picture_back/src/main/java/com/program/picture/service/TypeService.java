package com.program.picture.service;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Type;

import java.util.List;

public interface TypeService {

    HttpResult insertPictureType(Integer typeId, Integer pictureId);

    HttpResult selectPictureType(Integer pictureId);

    HttpResult deletePictureType(Integer typeId, Integer pictureId);

}
