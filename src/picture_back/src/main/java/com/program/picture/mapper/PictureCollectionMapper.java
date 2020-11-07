package com.program.picture.mapper;

import com.program.picture.domain.entity.PictureCollection;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureCollection record);

    PictureCollection selectByPrimaryKey(Integer id);

    List<PictureCollection> selectAll();

    int updateByPrimaryKey(PictureCollection record);
}