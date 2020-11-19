package com.program.picture.service.Impl;

import com.program.picture.common.exception.type.PictureTypeAddFailException;
import com.program.picture.common.exception.type.PictureTypeDelFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Picture;
import com.program.picture.domain.entity.PictureType;
import com.program.picture.domain.entity.Type;
import com.program.picture.mapper.PictureTypeMapper;
import com.program.picture.mapper.TypeMapper;
import com.program.picture.service.TypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 10:29
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private PictureTypeMapper pictureTypeMapper;

    @Override
    public HttpResult insertPictureType(Integer typeId, Integer pictureId) {
        PictureType pictureType = PictureType.builder()
                .typeId(typeId)
                .pictureId(pictureId)
                .build();
        if (judgePictureType(typeId, pictureId)) {
            return HttpResult.success("该图片标签已存在");
        }
        if (pictureTypeMapper.insert(pictureType) == 0) {
            throw new PictureTypeAddFailException("图片标签添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectPictureType(Integer pictureId) {
        List<PictureType> pictureTypeList = pictureTypeMapper.selectByPictureId(pictureId);
        List<Type> typeList = new ArrayList<>();
        for (PictureType pictureType : pictureTypeList) {
            Type type = typeMapper.selectByPrimaryKey(pictureType.getTypeId());
            typeList.add(type);
        }
        if (typeList.size() == 0) {
            return HttpResult.success("该图片无标签");
        }
        return HttpResult.success(typeList);
    }

    @Override
    public HttpResult deletePictureType(Integer typeId, Integer pictureId) {
        int delete = 1;
        List<PictureType> pictureTypeList = pictureTypeMapper.selectByPictureId(pictureId);
        for (PictureType pictureType : pictureTypeList) {
            if (pictureType.getTypeId().equals(typeId)) {
                delete = pictureTypeMapper.deleteByPrimaryKey(pictureType.getId());
            }
        }
        if (delete == 0){
            throw new PictureTypeDelFailException("图片标签删除失败");
        }
        return HttpResult.success();
    }

    private boolean judgePictureType(Integer typeId, Integer pictureId) {
        List<PictureType> pictureTypeList = pictureTypeMapper.selectByPictureId(pictureId);
        for (PictureType pictureType : pictureTypeList) {
            if (pictureType.getTypeId().equals(typeId)) {
                return true;
            }
        }
        return false;
    }
}
