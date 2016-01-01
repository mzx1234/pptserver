package com.gdut.pptserver.service.impl;

import com.gdut.pptserver.exception.ServiceException;
import com.gdut.pptserver.service.ParseService;
import com.gdut.pptserver.utility.POIParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 解析PPT文件服务实现类
 * Created by zison on 2015/12/30.
 */
@Service
public class ParseServiceImpl implements ParseService {

    @Autowired
    private POIParse poiParse;

    @Override
    public void parsePPTFile(String file) throws ServiceException {
        try {
            poiParse.getPPTSlides(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        poiParse.getImageBuffer(0);
    }

    @Override
    public void parsePPTXFile(String file) throws ServiceException {
        try {
            poiParse.getPPTXSlides(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        poiParse.getImageBuffer(0);
    }
}
