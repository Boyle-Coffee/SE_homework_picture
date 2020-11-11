package com.program.picture.service.Impl;

import com.program.picture.common.exception.galley.GalleyAddFailException;
import com.program.picture.common.exception.galley.GalleyDelFailException;
import com.program.picture.common.exception.galley.GalleySelectFailException;
import com.program.picture.common.exception.galley.GalleyUpdateFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Galley;
import com.program.picture.mapper.GalleyMapper;
import com.program.picture.service.GalleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-11 15:17
 **/
@Service
public class GalleyServiceImpl implements GalleyService {

    @Autowired
    private GalleyMapper galleyMapper;

    @Override
    public HttpResult deleteByPrimaryKey(Integer id) {
        if (galleyMapper.deleteByPrimaryKey(id) == 0) {
            throw new GalleyDelFailException("图库删除失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult insert(Galley record) {
        if (galleyMapper.insert(record) == 0) {
            throw new GalleyAddFailException("图库添加失败");
        }
        return HttpResult.success();
    }

    @Override
    public HttpResult selectByPrimaryKey(Integer id) {
        Galley galley = galleyMapper.selectByPrimaryKey(id);
        if (galley == null) {
            throw new GalleySelectFailException("图库查找失败");
        }
        return HttpResult.success(galley);
    }

    @Override
    public HttpResult selectAll() {
        List<Galley> galleyList = galleyMapper.selectAll();
        if (galleyList == null || galleyList.size() == 0) {
            throw new GalleySelectFailException("图库查找失败");
        }
        return HttpResult.success(galleyList);
    }

    @Override
    public HttpResult updateByPrimaryKey(Galley record) {
        if (galleyMapper.updateByPrimaryKey(record) == 0) {
            throw new GalleyUpdateFailException("图库更新失败");
        }
        return HttpResult.success();
    }
}
