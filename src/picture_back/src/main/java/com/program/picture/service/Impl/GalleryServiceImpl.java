package com.program.picture.service.Impl;

import com.program.picture.common.exception.gallery.*;
import com.program.picture.common.exception.picture.PictureSelectFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.domain.entity.GalleryPicture;
import com.program.picture.domain.entity.Picture;
import com.program.picture.mapper.GalleryMapper;
import com.program.picture.mapper.GalleryPictureMapper;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 15:17
 **/
@Service
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private GalleryPictureMapper galleryPictureMapper;

    @Override
    public HttpResult deleteByPrimaryKey(Integer id, Integer userId) {
        if (!galleryMapper.selectByPrimaryKey(id).getUserId().equals(userId)) {
            throw new GalleryDelFailException("图库删除失败——该用户无权限删除该图库");
        }
        if (galleryMapper.deleteByPrimaryKey(id) == 0) {
            throw new GalleryDelFailException("图库删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult insert(Gallery record) {
        if (galleryMapper.insert(record) == 0) {
            throw new GalleryAddFailException("图库添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectByPrimaryKey(Integer id) {
        Gallery gallery = galleryMapper.selectByPrimaryKey(id);
        if (gallery == null) {
            throw new GallerySelectFailException("图库查找失败");
        }
        return HttpResult.success(gallery);
    }

    @Override
    public HttpResult selectAll() {
        List<Gallery> galleryList = galleryMapper.selectAll();
        if (galleryList == null || galleryList.size() == 0) {
            throw new GallerySelectFailException("图库查找失败");
        }
        return HttpResult.success(galleryList);
    }

    @Override
    public HttpResult updateByPrimaryKey(Gallery record) {
        if (galleryMapper.updateByPrimaryKey(record) == 0) {
            throw new GalleryUpdateFailException("图库更新失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult insertGalleryPicture(Integer galleryId, Integer pictureId) {
        GalleryPicture galleryPicture = GalleryPicture.builder()
                .galleryId(galleryId)
                .pictureId(pictureId)
                .build();
        if (galleryPictureMapper.insert(galleryPicture) == 0) {
            throw new GalleryPictureAddFailException("图库图片添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult deleteGalleryPicture(Integer galleryId, Integer pictureId, Integer userId) {
        if (!galleryMapper.selectByPrimaryKey(galleryId).getUserId().equals(userId)) {
            throw new GalleryPictureDelFailException("图库图片删除失败——该用户无权限");
        }
        int delete = 1;
        List<GalleryPicture> galleryPictureList = galleryPictureMapper.selectByGalleryId(galleryId);
        for (GalleryPicture galleryPicture : galleryPictureList) {
            if (galleryPicture.getPictureId().equals(pictureId)) {
                delete = galleryPictureMapper.deleteByPrimaryKey(galleryPicture.getId());
            }
        }
        if (delete == 0) {
            throw new GalleryPictureDelFailException("图库图片删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectGalleryByUserId(Integer userId) {
        List<Gallery> galleryList = galleryMapper.selectByUserId(userId);
        if (galleryList == null || galleryList.size() == 0) {
            return HttpResult.success("该用户无图库");
        }
        return HttpResult.success(galleryList);
    }

    @Override
    public HttpResult selectPictureByGallery(Integer galleryId) {
        List<GalleryPicture> galleryPictureList = galleryPictureMapper.selectByGalleryId(galleryId);
        List<Picture> pictureList = new ArrayList<>();
        if (galleryPictureList.size() == 0) {
            return HttpResult.success("该图库无图片");
        }
        for (GalleryPicture galleryPicture : galleryPictureList) {
            Picture picture = pictureMapper.selectByPrimaryKey(galleryPicture.getPictureId());
            pictureList.add(picture);
        }
        return HttpResult.success(pictureList);
    }


}
