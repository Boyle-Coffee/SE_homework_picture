package com.program.picture.mapper;

import com.program.picture.domain.entity.UserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDetails record);

    UserDetails selectByPrimaryKey(Integer id);

    List<UserDetails> selectAll();

    int updateByPrimaryKey(UserDetails record);
}