package com.program.picture.service.Impl;

import com.program.picture.common.exception.picture.PictureAddFailException;
import com.program.picture.common.exception.picture.PictureDelFailException;
import com.program.picture.common.exception.picture.PictureSelectFailException;
import com.program.picture.common.exception.picture.PictureUpdateFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-07 20:15
 **/
@Service
public class PictureServiceImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);


    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public HttpResult deleteByPrimaryKey(Integer id) {
        if (pictureMapper.deleteByPrimaryKey(id) == 0) {
            throw new PictureDelFailException("图片删除失败");
        }
        logger.info("删除图片" + id);
        return HttpResult.success();
    }

    @Override
    public HttpResult insert(Picture record) {
        // todo  违规图片 ——》 ——》 以图搜图（放入数据库）——》放云端
        if (pictureMapper.insert(record) == 0) {
            throw new PictureAddFailException("图片添加失败");
        }
        logger.info("添加图片" + record);
        return HttpResult.success();
    }

    @Override
    public HttpResult selectByPrimaryKey(Integer id) {
        Picture picture = pictureMapper.selectByPrimaryKey(id);
        if (picture == null) {
            throw new PictureSelectFailException("图片查找失败");
        }
        logger.info("查找图片" + picture);
        return HttpResult.success(picture);
    }

    @Override
    public HttpResult selectAll() {
        List<Picture> pictureList = pictureMapper.selectAll();
        if (pictureList == null || pictureList.size() == 0) {
            throw new PictureSelectFailException("图片查找失败");
        }
        logger.info("查找图片" + pictureList);
        return HttpResult.success(pictureList);
    }

    @Override
    public HttpResult updateByPrimaryKey(Picture record) {
        if (pictureMapper.updateByPrimaryKey(record) == 0) {
            throw new PictureUpdateFailException("图片更新失败");
        }
        logger.info("更新图片" + record);
        return HttpResult.success();
    }
}
