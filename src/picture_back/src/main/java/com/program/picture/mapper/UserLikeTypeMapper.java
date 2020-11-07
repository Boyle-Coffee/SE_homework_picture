package com.program.picture.mapper;

import com.program.picture.domain.entity.UserLikeType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserLikeTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLikeType record);

    UserLikeType selectByPrimaryKey(Integer id);

    List<UserLikeType> selectAll();

    int updateByPrimaryKey(UserLikeType record);
}