package com.gdut.pptserver.service;

import com.gdut.pptserver.exception.ServiceException;

/**
 * 解析PPT文件服务
 * Created by zison on 2015/12/30.
 */
public interface ParseService {


    /**
     * 解析对应的PPT文件
     * @param file 文件路径
     * @throws ServiceException
     */
    void parsePPTFile(String file) throws ServiceException;


    void parsePPTXFile(String file) throws ServiceException;
}
