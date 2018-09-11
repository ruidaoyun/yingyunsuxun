package com.belle.yingyunsuxun.dao;

import com.belle.yingyunsuxun.model.entity.Shop;

import java.util.List;

public interface ShopDao {
    List<Shop> selectShopListByUserId(Integer userId);

    List<Shop> selectShopListByShopNameOrShopNumber(String shopName);

    Integer insertIntoLocationImg();
}
