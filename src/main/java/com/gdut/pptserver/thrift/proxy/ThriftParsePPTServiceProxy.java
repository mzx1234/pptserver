package com.gdut.pptserver.thrift.proxy;

import com.gdut.pptserver.application.GlobalApplication;
import com.gdut.pptserver.service.ParseService;
import com.gdut.pptserver.thrift.service.TParsePPTService.Iface;
import com.gdut.pptserver.thrift.struct.PPTBytes;
import com.gdut.pptserver.thrift.struct.PPTDetail;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zison on 2016/1/7.
 */
@Service
public class ThriftParsePPTServiceProxy implements Iface{

    @Autowired
    private ParseService parseService;
    private GlobalApplication globalApplication;
    @Override
    public PPTBytes parsePPTAndGetFirst(PPTDetail parm) throws TException {
        String path = parm.path;
        byte[] bytes = parseService.parsePPTFile(path);
        PPTBytes pptBytes = new PPTBytes();
        PPTDetail pptDetail = new PPTDetail();
        pptDetail.path = globalApplication.getPath();
        pptDetail.fileName = globalApplication.getFileName();
        pptDetail.curPage = globalApplication.getCurPage();
        pptBytes.pptDetail = pptDetail;
        pptBytes.setBytes(bytes);

        return pptBytes;
    }
}
