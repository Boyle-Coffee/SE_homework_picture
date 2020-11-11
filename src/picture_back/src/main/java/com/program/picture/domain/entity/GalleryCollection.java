package com.program.picture.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class GalleryCollection {
    private Integer id;

    private Integer galleryId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    @Tolerate
    public GalleryCollection() {
    }
}