package com.program.picture.service.Impl;

import com.program.picture.common.exception.collection.GalleryCollectionAddFailException;
import com.program.picture.common.exception.collection.GalleryCollectionDelFailException;
import com.program.picture.common.exception.collection.PictureCollectionAddFailException;
import com.program.picture.common.exception.collection.PictureCollectionDelFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.domain.entity.GalleryCollection;
import com.program.picture.domain.entity.Picture;
import com.program.picture.domain.entity.PictureCollection;
import com.program.picture.mapper.GalleryCollectionMapper;
import com.program.picture.mapper.GalleryMapper;
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
    private GalleryMapper galleryMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private GalleryCollectionMapper galleryCollectionMapper;

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
    public HttpResult deletePictureCollection(Integer userId, Integer pictureId) {
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

    @Override
    public HttpResult insertGalleryCollection(Integer userId, Integer galleryId) {
        GalleryCollection galleryCollection = GalleryCollection.builder()
                .userId(userId)
                .galleryId(galleryId)
                .build();
        if (judgeGalleryCollection(userId, galleryId)) {
            return HttpResult.success("该用户已收藏该图库");
        }
        if (galleryCollectionMapper.insert(galleryCollection) == 0) {
            throw new GalleryCollectionAddFailException("图库收藏添加失败");
        }
        return HttpResult.success();
    }
    
    @Override
    public HttpResult deleteGalleryCollection(Integer userId, Integer galleryId) {
        int delete = 1;
        List<GalleryCollection> galleryCollectionList = galleryCollectionMapper.selectByUserId(userId);
        for (GalleryCollection galleryCollection : galleryCollectionList) {
            if (galleryCollection.getGalleryId().equals(galleryId)) {
                delete = galleryCollectionMapper.deleteByPrimaryKey(galleryCollection.getId());
            }
        }
        if (delete == 0) {
            throw new GalleryCollectionDelFailException("图库收藏删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectGalleryCollection(Integer userId) {
        List<GalleryCollection> galleryCollectionList = galleryCollectionMapper.selectByUserId(userId);
        List<Gallery> galleryList = new ArrayList<>();
        for (GalleryCollection galleryCollection : galleryCollectionList) {
            Gallery gallery = galleryMapper.selectByPrimaryKey(galleryCollection.getGalleryId());
            galleryList.add(gallery);
        }
        if (galleryList.size() == 0) {
            return HttpResult.success("该用户无收藏图库");
        }
        return HttpResult.success(galleryList);
    }


    private boolean judgeGalleryCollection(Integer userId, Integer galleryId) {
        List<GalleryCollection> galleryCollectionList = galleryCollectionMapper.selectByUserId(userId);
        for (GalleryCollection galleryCollection : galleryCollectionList) {
            if (galleryCollection.getGalleryId().equals(galleryId)) {
                return true;
            }
        }
        return false;
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
