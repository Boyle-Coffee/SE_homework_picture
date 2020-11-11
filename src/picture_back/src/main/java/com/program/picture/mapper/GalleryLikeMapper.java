package com.program.picture.mapper;

import com.program.picture.domain.entity.GalleryLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GalleryLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GalleryLike record);

    GalleryLike selectByPrimaryKey(Integer id);

    List<GalleryLike> selectAll();

    int updateByPrimaryKey(GalleryLike record);

    List<GalleryLike> selectByUserId(Integer userId);
}