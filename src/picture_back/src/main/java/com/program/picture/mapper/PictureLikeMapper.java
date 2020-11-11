package com.program.picture.mapper;

import com.program.picture.domain.entity.PictureLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PictureLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureLike record);

    PictureLike selectByPrimaryKey(Integer id);

    List<PictureLike> selectAll();

    int updateByPrimaryKey(PictureLike record);

    List<PictureLike> selectByUserId(Integer userId);
}