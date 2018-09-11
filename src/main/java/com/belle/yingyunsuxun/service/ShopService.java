package com.belle.yingyunsuxun.service;

import com.belle.yingyunsuxun.model.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> selectShopListByUserId(Integer userId);

    List<Shop> selectShopListByShopName(String shopName);
}
