package com.program.picture.mapper;

import com.program.picture.domain.entity.CommentPicture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentPictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentPicture record);

    CommentPicture selectByPrimaryKey(Integer id);

    List<CommentPicture> selectAll();

    int updateByPrimaryKey(CommentPicture record);

    List<CommentPicture> selectByPictureId(Integer pictureId);
}