package com.program.picture.service.Impl;

import com.program.picture.common.exception.comment.CommentPictureAddFailException;
import com.program.picture.common.exception.comment.CommentPictureDelFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.CommentGallery;
import com.program.picture.domain.entity.CommentPicture;
import com.program.picture.domain.entity.Gallery;
import com.program.picture.domain.entity.Picture;
import com.program.picture.mapper.CommentGalleryMapper;
import com.program.picture.mapper.CommentPictureMapper;
import com.program.picture.mapper.GalleryMapper;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-13 10:32
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private CommentPictureMapper commentPictureMapper;

    @Autowired
    private CommentGalleryMapper commentGalleryMapper;

    @Override
    public HttpResult insertCommentPicture(CommentPicture record) {
        if (commentPictureMapper.insert(record) == 0) {
            throw new CommentPictureAddFailException("图片评论添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult insertCommentGallery(CommentGallery record) {
        if (commentGalleryMapper.insert(record) == 0) {
            throw new CommentPictureAddFailException("图库评论添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult deleteCommentPicture(Integer commentId, Integer userId) {
        if (!judegCommentPicture(userId, commentId)) {
            throw new CommentPictureDelFailException("图片评论删除失败——无权限");
        }
        if (commentPictureMapper.deleteByPrimaryKey(commentId) == 0) {
            throw new CommentPictureDelFailException("图片评论删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult deleteCommentGallery(Integer commentId, Integer userId) {
        if (!judegCommentGallery(userId, commentId)) {
            throw new CommentPictureDelFailException("图库评论删除失败——无权限");
        }
        if (commentGalleryMapper.deleteByPrimaryKey(commentId) == 0) {
            throw new CommentPictureDelFailException("图库评论删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectCommentPicture(Integer pictureId) {
        List<CommentPicture> commentPictureList = commentPictureMapper.selectByPictureId(pictureId);
        if (commentPictureList.size() == 0) {
            return HttpResult.success("该图片无评论");
        }
        return HttpResult.success(commentPictureList);
    }

    @Override
    public HttpResult selectCommentGallery(Integer galleryId) {
        List<CommentGallery> commentGalleryList = commentGalleryMapper.selectByGalleryId(galleryId);
        if (commentGalleryList.size() == 0) {
            return HttpResult.success("该图库无评论");
        }
        return HttpResult.success(commentGalleryList);
    }

    private boolean judegCommentPicture(Integer userId, Integer commentId) {
        CommentPicture commentPicture = commentPictureMapper.selectByPrimaryKey(commentId);
        Picture picture = pictureMapper.selectByPrimaryKey(commentPicture.getPictureId());
        return commentPicture.getUserId().equals(userId) || picture.getUserId().equals(userId);
    }

    private boolean judegCommentGallery(Integer userId, Integer commentId) {
        CommentGallery commentGallery = commentGalleryMapper.selectByPrimaryKey(commentId);
        Gallery gallery = galleryMapper.selectByPrimaryKey(commentGallery.getGalleryId());
        return commentGallery.getUserId().equals(userId) || gallery.getUserId().equals(userId);
    }
}
