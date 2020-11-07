package com.program.picture.mapper;

import com.program.picture.domain.entity.CommentGallery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentGalleryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentGallery record);

    CommentGallery selectByPrimaryKey(Integer id);

    List<CommentGallery> selectAll();

    int updateByPrimaryKey(CommentGallery record);
}