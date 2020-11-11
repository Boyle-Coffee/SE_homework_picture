package com.program.picture.service.Impl;

import com.program.picture.common.exception.collection.PictureCollectionAddFailException;
import com.program.picture.common.exception.collection.PictureCollectionDelFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;
import com.program.picture.domain.entity.PictureCollection;
import com.program.picture.mapper.PictureCollectionMapper;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 11:09
 **/
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private PictureCollectionMapper pictureCollectionMapper;

    @Override
    public HttpResult insertPictureCollection(Integer userId, Integer pictureId) {
        PictureCollection pictureCollection = PictureCollection.builder()
                .userId(userId)
                .pictureId(pictureId)
                .build();
        if (judgePictureCollection(userId, pictureId)) {
            return HttpResult.success("该用户已收藏该图片");
        }
        if (pictureCollectionMapper.insert(pictureCollection) == 0) {
            throw new PictureCollectionAddFailException("图片收藏添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult delectPictureCollection(Integer userId, Integer pictureId) {
        int delete = 1;
        List<PictureCollection> pictureCollectionList = pictureCollectionMapper.selectByUserId(userId);
        for (PictureCollection pictureCollection : pictureCollectionList) {
            if (pictureCollection.getPictureId().equals(pictureId)) {
                delete = pictureCollectionMapper.deleteByPrimaryKey(pictureCollection.getId());
            }
        }
        if (delete == 0) {
            throw new PictureCollectionDelFailException("图片收藏删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectPictureCollection(Integer userId) {
        List<PictureCollection> pictureCollectionList = pictureCollectionMapper.selectByUserId(userId);
        List<Picture> pictureList = new ArrayList<>();
        for (PictureCollection pictureCollection : pictureCollectionList) {
            Picture picture = pictureMapper.selectByPrimaryKey(pictureCollection.getPictureId());
            pictureList.add(picture);
        }
        if (pictureList.size() == 0) {
            return HttpResult.success("该用户无收藏图片");
        }
        return HttpResult.success(pictureList);
    }

    private boolean judgePictureCollection(Integer userId, Integer pictureId) {
        List<PictureCollection> pictureCollectionList = pictureCollectionMapper.selectByUserId(userId);
        for (PictureCollection pictureCollection : pictureCollectionList) {
            if (pictureCollection.getPictureId().equals(pictureId)) {
                return true;
            }
        }
        return false;
    }
}
