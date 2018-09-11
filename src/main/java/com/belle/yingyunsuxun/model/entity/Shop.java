package com.belle.yingyunsuxun.model.entity;

import lombok.Data;

@Data
public class Shop {
    private Integer shopId;
    private String shopName;
    private String shopNumber;//店铺编号
    private String longitude;//经度
    private String latitude;//纬度
}
