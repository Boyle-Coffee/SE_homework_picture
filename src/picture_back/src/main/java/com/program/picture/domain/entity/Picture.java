package com.program.picture.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class Picture {
    private Integer id;

    private Integer userId;

    private String name;

    private Integer open;

    private String path;

    private Date createTime;

    private Date updateTime;

    @Tolerate
    public Picture() {
    }
}