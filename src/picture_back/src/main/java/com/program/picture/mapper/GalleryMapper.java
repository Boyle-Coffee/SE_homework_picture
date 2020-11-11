package com.program.picture.mapper;

import com.program.picture.domain.entity.Gallery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GalleryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gallery record);

    Gallery selectByPrimaryKey(Integer id);

    List<Gallery> selectAll();

    int updateByPrimaryKey(Gallery record);
}