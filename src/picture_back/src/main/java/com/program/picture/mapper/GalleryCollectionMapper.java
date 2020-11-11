package com.program.picture.mapper;

import com.program.picture.domain.entity.GalleryCollection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GalleryCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GalleryCollection record);

    GalleryCollection selectByPrimaryKey(Integer id);

    List<GalleryCollection> selectAll();

    int updateByPrimaryKey(GalleryCollection record);

    List<GalleryCollection> selectByUserId(Integer userId);
}