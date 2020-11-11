package com.program.picture.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class PictureCollection {
    private Integer id;

    private Integer pictureId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    @Tolerate
    public PictureCollection() {
    }
}