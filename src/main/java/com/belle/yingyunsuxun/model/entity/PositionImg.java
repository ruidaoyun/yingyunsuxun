package com.belle.yingyunsuxun.model.entity;

import lombok.Data;

@Data
public class PositionImg {
    private Integer shopId;
    private Integer positionId;
    private Integer status;//0是前，1是后
    private String imgPath;
}
