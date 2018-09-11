package com.belle.yingyunsuxun.service.impl;

import com.belle.yingyunsuxun.dao.ShopDao;
import com.belle.yingyunsuxun.model.entity.Shop;
import com.belle.yingyunsuxun.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    public List<Shop> selectShopListByUserId(Integer userId) {
        return shopDao.selectShopListByUserId (userId);
    }

    @Override
    public List<Shop> selectShopListByShopName(String shopName) {
            shopName="%"+shopName+"%";
        return shopDao.selectShopListByShopNameOrShopNumber (shopName);
    }
}
