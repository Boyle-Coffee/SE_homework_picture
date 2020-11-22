package com.program.picture.mapper;

import com.program.picture.domain.entity.Picture;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    Picture selectByPrimaryKey(Integer id);

    List<Picture> selectAll();

    int updateByPrimaryKey(Picture record);

    List<Picture> selectByUserId(Integer userId);

    Picture selectByPictureUrl(String path);
}