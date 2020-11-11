package com.program.picture.mapper;

import com.program.picture.domain.entity.Galley;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GalleyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Galley record);

    Galley selectByPrimaryKey(Integer id);

    List<Galley> selectAll();

    int updateByPrimaryKey(Galley record);
}