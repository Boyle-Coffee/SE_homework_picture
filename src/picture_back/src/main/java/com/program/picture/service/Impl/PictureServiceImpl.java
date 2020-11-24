package com.program.picture.service.Impl;

import com.program.picture.common.exception.picture.PictureAddFailException;
import com.program.picture.common.exception.picture.PictureDelFailException;
import com.program.picture.common.exception.picture.PictureSelectFailException;
import com.program.picture.common.exception.picture.PictureUpdateFailException;
import com.program.picture.common.result.HttpResult;
import com.program.picture.common.util.HttpRequestUtil;
import com.program.picture.domain.entity.Picture;
import com.program.picture.domain.entity.PictureType;
import com.program.picture.mapper.PictureMapper;
import com.program.picture.mapper.PictureTypeMapper;
import com.program.picture.service.PictureService;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: picture
 * @description:
 * @author: Mr.Huang
 * @create: 2020-11-07 20:15
 **/
@Service
public class PictureServiceImpl implements PictureService {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    @Resource
    private HttpRequestUtil httpRequestUtil;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private PictureTypeMapper pictureTypeMapper;

    @Override
    public HttpResult deleteByPrimaryKey(Integer id, Integer userId) {
        if (!pictureMapper.selectByPrimaryKey(id).getUserId().equals(userId)) {
            throw new PictureDelFailException("图片删除失败——该用户无权限删除");
        }
        if (pictureMapper.deleteByPrimaryKey(id) == 0) {
            throw new PictureDelFailException("图片删除失败");
        }
        JSONObject jsonParamDelete = new JSONObject();
        jsonParamDelete.put("pid", id + "");
        String paramDelete = jsonParamDelete.toJSONString();
        String resultDelete =
                HttpRequestUtil.sendPost("http://120.79.50.99:8100/imageDelete",
                        paramDelete);
        JSONObject resultDeleteJson = JSONObject.parseObject(resultDelete);
        boolean delete = (boolean) resultDeleteJson.get("isSuccess");
        if (!delete) {
            throw new PictureDelFailException("图片删除失败");
        }
        logger.info("删除图片" + id);
        return HttpResult.success();
    }

    @Override
    public HttpResult insert(Picture record) {
        JSONObject jsonParamRecognition = new JSONObject();
        jsonParamRecognition.put("url", record.getPath());
        String paramRecognition = jsonParamRecognition.toJSONString();
        String resultRecognition =
                HttpRequestUtil.sendPost("http://120.79.50.99:8100/imageRecognition",
                        paramRecognition);
        JSONObject resultRecognitionJson = JSONObject.parseObject(resultRecognition);
        boolean recognition = (boolean) resultRecognitionJson.get("isSuccess");
        if (!recognition) {
            throw new PictureAddFailException("图片添加失败——该图片为违规图片");
        }
        if (pictureMapper.insert(record) == 0) {
            throw new PictureAddFailException("图片添加失败");
        }
        Picture picture = pictureMapper.selectByPictureUrl(record.getPath());
        JSONObject jsonParamInsert = new JSONObject();
        jsonParamInsert.put("pid", picture.getId() + "");
        jsonParamInsert.put("url", picture.getPath());
        String paramInsert = jsonParamInsert.toJSONString();
        String resultInsert =
                HttpRequestUtil.sendPost("http://120.79.50.99:8100/imageInsert",
                        paramInsert);
        JSONObject resultInsertJson = JSONObject.parseObject(resultInsert);
        boolean insert = (boolean) resultInsertJson.get("isSuccess");
        if (!insert) {
            throw new PictureAddFailException("图片添加失败");
        }
        logger.info("添加图片" + record);
        return HttpResult.success(picture.getId());
    }

    @Override
    public HttpResult selectByPrimaryKey(Integer id) {
        Picture picture = pictureMapper.selectByPrimaryKey(id);
        if (picture == null) {
            throw new PictureSelectFailException("图片查找失败");
        }
        logger.info("查找图片" + picture);
        return HttpResult.success(picture);
    }

    @Override
    public HttpResult selectAll(Integer isAsc) {
        List<Picture> pictureList = pictureMapper.selectAll();
        if (pictureList == null || pictureList.size() == 0) {
            throw new PictureSelectFailException("图片查找失败");
        }
        if (isAsc != 1) {
            Collections.reverse(pictureList);
        }
        logger.info("查找图片" + pictureList);
        return HttpResult.success(pictureList);
    }

    @Override
    public HttpResult updateByPrimaryKey(Picture record) {
        if (pictureMapper.updateByPrimaryKey(record) == 0) {
            throw new PictureUpdateFailException("图片更新失败");
        }
        logger.info("更新图片" + record);
        return HttpResult.success();
    }

    @Override
    public HttpResult selectPictureByType(Integer typeId) {
        List<PictureType> pictureTypeList = pictureTypeMapper.selectByTypeId(typeId);
        List<Picture> pictureList = new ArrayList<>();
        if (pictureTypeList == null || pictureTypeList.size() == 0) {
            return HttpResult.success("该标签无图片添加");
        }
        for (PictureType pictureType : pictureTypeList) {
            pictureList.add(pictureMapper.selectByPrimaryKey(pictureType.getPictureId()));
        }
        return HttpResult.success(pictureList);
    }

    @Override
    public HttpResult selectPictureByUserId(Integer userId) {
        List<Picture> pictureList = pictureMapper.selectByUserId(userId);
        if (pictureList == null || pictureList.size() == 0) {
            return HttpResult.success("该用户无添加图片");
        }
        return HttpResult.success(pictureList);
    }

    @Override
    public HttpResult selectSimilarPicture(String pictureUrl) {
        JSONObject jsonParamSimilar = new JSONObject();
        jsonParamSimilar.put("url", pictureUrl);
        String paramSimilar = jsonParamSimilar.toJSONString();
        String resultSimilar =
                HttpRequestUtil.sendPost("http://120.79.50.99:8100/imageSearch",
                        paramSimilar);
        JSONObject resultSimilarJson = JSONObject.parseObject(resultSimilar);
        String[] dataArray = (String[]) resultSimilarJson.get("data");
        if (dataArray == null || dataArray.length == 0) {
            return HttpResult.success("该图片无相似图片");
        }
        int[] dataIntArray = new int[dataArray.length];
        for (int i = 0; i < dataArray.length; i++) {
            dataIntArray[i] = Integer.parseInt(dataArray[i]);
        }
        return HttpResult.success(dataIntArray);
    }

    @Override
    public HttpResult selectByContent(String content) {
        List<Picture> pictureList = pictureMapper.selectByName(content);
        if (pictureList.size() == 0) {
            return HttpResult.success("该内容无相关图片");
        }
        return HttpResult.success(pictureList);
    }
}
