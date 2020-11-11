package com.program.picture.service.Impl;

import com.program.picture.common.exception.gallery.GalleryAddFailException;
import com.program.picture.common.exception.gallery.GalleryDelFailException;
import com.program.picture.common.exception.gallery.GallerySelectFailException;
import com.program.picture.common.exception.gallery.GalleryUpdateFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.mapper.GalleryMapper;
import com.program.picture.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public HttpResult deleteByPrimaryKey(Integer id) {
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
}
