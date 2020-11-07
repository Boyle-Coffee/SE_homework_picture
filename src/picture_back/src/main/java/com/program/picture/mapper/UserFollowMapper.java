package com.program.picture.mapper;

import com.program.picture.domain.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFollow record);

    UserFollow selectByPrimaryKey(Integer id);

    List<UserFollow> selectAll();

    int updateByPrimaryKey(UserFollow record);
}