package com.program.picture.mapper;

import com.program.picture.domain.entity.PictureType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureType record);

    PictureType selectByPrimaryKey(Integer id);

    List<PictureType> selectAll();

    int updateByPrimaryKey(PictureType record);

    List<PictureType> selectByPictureId(Integer picictureId);

    List<PictureType> selectByTypeId(Integer typeId);
}