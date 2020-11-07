package com.program.picture.mapper;

import com.program.picture.domain.entity.Picture;
import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    Picture selectByPrimaryKey(Integer id);

    List<Picture> selectAll();

    int updateByPrimaryKey(Picture record);
}