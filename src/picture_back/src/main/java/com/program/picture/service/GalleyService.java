package com.program.picture.service;

import com.program.picture.common.result.HttpResult;
import com.program.picture.domain.entity.Galley;

import java.util.List;

public interface GalleyService {

    HttpResult deleteByPrimaryKey(Integer id);

    HttpResult insert(Galley record);

    HttpResult selectByPrimaryKey(Integer id);

    HttpResult selectAll();

    HttpResult updateByPrimaryKey(Galley record);
}
