package com.program.picture.service.Impl;

import com.program.picture.common.exception.like.PictureLikeDelFailException;
import com.program.picture.common.exception.picture.PictureAddFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;
import com.program.picture.domain.entity.PictureLike;
import com.program.picture.mapper.PictureLikeMapper;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 11:39
 **/
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private PictureLikeMapper pictureLikeMapper;

    @Override
    public HttpResult selectPictureLike(Integer userId) {
        List<PictureLike> pictureLikeList = pictureLikeMapper.selectByUserId(userId);
        List<Picture> pictureList = new ArrayList<>();
        for (PictureLike pictureLike : pictureLikeList) {
            Picture picture = pictureMapper.selectByPrimaryKey(pictureLike.getId());
            pictureList.add(picture);
        }
        if (pictureLikeList.size() == 0) {
            return HttpResult.success("该用户无添加喜欢的图片");
        }
        return HttpResult.success(pictureList);
    }

    @Override
    public HttpResult deletePictureLike(Integer userId, Integer pictureId) {
        int delete = 1;
        List<PictureLike> pictureLikeList = pictureLikeMapper.selectByUserId(userId);
        for (PictureLike pictureLike : pictureLikeList) {
            if (pictureLike.getPictureId().equals(pictureId)) {
                delete = pictureLikeMapper.deleteByPrimaryKey(pictureLike.getId());
            }
        }
        if (delete == 0) {
            throw new PictureLikeDelFailException("喜欢图片删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult addPictureLike(Integer userId, Integer pictureId) {
        PictureLike pictureLike = PictureLike.builder()
                .userId(userId)
                .pictureId(pictureId)
                .build();
        if (judgePictureLike(userId, pictureId)) {
            return HttpResult.success("该图片存在我的喜欢中");
        }
        if (pictureLikeMapper.insert(pictureLike) == 0) {
            throw new PictureAddFailException("喜欢图片添加失败");
        }
        return HttpResult.success();
    }

    private boolean judgePictureLike(Integer userId, Integer pictureId) {
        List<PictureLike> pictureLikeList = pictureLikeMapper.selectByUserId(userId);
        for (PictureLike pictureLike : pictureLikeList) {
            if (pictureLike.getPictureId().equals(pictureId)) {
                return true;
            }
        }
        return false;
    }
}
