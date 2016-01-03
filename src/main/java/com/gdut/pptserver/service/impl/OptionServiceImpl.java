package com.gdut.pptserver.service.impl;

import com.gdut.pptserver.application.GlobalApplication;
import com.gdut.pptserver.exception.ServiceException;
import com.gdut.pptserver.service.OptionService;
import com.gdut.pptserver.utility.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zison on 2015/12/31.
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private GlobalApplication globalApplication;

    @Override

    public byte[] swichPPTPage(int cur) {
        byte[] result = redisUtil.hGetBytes(globalApplication.getKey(), cur+"");
        if(result == null) {
            throw new ServiceException();
        }
        return result;
    }
}

