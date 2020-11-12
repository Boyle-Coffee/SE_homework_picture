package com.program.picture.mapper;

import com.program.picture.domain.entity.GalleryPicture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GalleryPictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GalleryPicture record);

    GalleryPicture selectByPrimaryKey(Integer id);

    List<GalleryPicture> selectAll();

    int updateByPrimaryKey(GalleryPicture record);

    List<GalleryPicture> selectByGalleryId(Integer galleryId);
}