package com.program.picture.mapper;

import com.program.picture.domain.entity.Galley;
import java.util.List;

public interface GalleyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Galley record);

    Galley selectByPrimaryKey(Integer id);

    List<Galley> selectAll();

    int updateByPrimaryKey(Galley record);
}