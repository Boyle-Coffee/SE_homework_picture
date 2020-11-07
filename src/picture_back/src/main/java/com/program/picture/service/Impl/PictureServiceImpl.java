package com.program.picture.service.Impl;

import com.program.picture.domain.entity.Picture;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-07 20:15
 **/
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int insert(Picture record) {
        return pictureMapper.insert(record);
    }
}
