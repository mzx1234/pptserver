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
    public byte[] parsePPTFile(String file) throws ServiceException {
        try {
            return poiParse.parsePPTAndGetFirst(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public byte[] parsePPTXFile(String file) throws ServiceException {
        try {
            return poiParse.parsePPTXAndGetFirst(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
