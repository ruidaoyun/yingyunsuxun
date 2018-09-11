package com.belle.yingyunsuxun.service.impl;

import com.belle.yingyunsuxun.dao.PositionImgDao;
import com.belle.yingyunsuxun.model.entity.PositionImg;
import com.belle.yingyunsuxun.service.PositionImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionImgServiceImpl implements PositionImgService {
    @Autowired
    private PositionImgDao positionImgDao;

    @Override
    public Integer insertIntoPositionImgDao(PositionImg positionImg) {
        return positionImgDao.insertIntoPositionImgDao (positionImg);
    }
}
