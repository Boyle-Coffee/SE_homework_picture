package com.program.picture.service;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;

import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-07 20:15
 **/
public interface PictureService {

    HttpResult deleteByPrimaryKey(Integer id, Integer userId);

    HttpResult insert(Picture record);

    HttpResult selectByPrimaryKey(Integer id);

    HttpResult selectAll(Integer isAsc);

    HttpResult updateByPrimaryKey(Picture record);

    HttpResult selectPictureByType(Integer typeId);

    HttpResult selectPictureByUserId(Integer userId);

    HttpResult selectSimilarPicture(String pictureUrl);

    HttpResult selectByContent(String content);
}
