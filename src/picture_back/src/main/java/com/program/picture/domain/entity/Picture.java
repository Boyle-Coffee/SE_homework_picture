package com.program.picture.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.program.picture.mapper.UserMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Builder
@ApiModel(value = "Picture", description = "图片类")
public class Picture {

    @Autowired
    private UserMapper userMapper;

    @ApiModelProperty(value = "图片id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "用户名称", example = "李大雷")
    private String userName;

    @ApiModelProperty(value = "用户id", example = "1")
    private Integer userId;

    @ApiModelProperty(value = "图片名称", example = "广工宿舍")
    private String name;

    @ApiModelProperty(value = "是否公开", example = "1为公开，0为不公开")
    private Integer open;

    @ApiModelProperty(value = "图片地址", example = "https://hyyyms-1301925880.cos.ap-guangzhou.myqcloud.com/pictureWork/13591605325285287.jpg")
    private String path;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Date createTime;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Tolerate
    public Picture() {
        this.userName = userMapper.selectByPrimaryKey(userId).getNickname();
    }
}