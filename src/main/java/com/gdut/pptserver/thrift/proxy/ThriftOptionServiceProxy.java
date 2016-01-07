package com.gdut.pptserver.thrift.proxy;

import com.gdut.pptserver.service.OptionService;
import com.gdut.pptserver.thrift.service.TOptionService;
import com.gdut.pptserver.thrift.struct.PPTBytes;
import com.gdut.pptserver.thrift.struct.PPTDetail;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * Created by zison on 2016/1/7.
 */
@Service
public class ThriftOptionServiceProxy implements TOptionService.Iface {

    @Autowired
    private OptionService optionService;

    @Override
    public PPTBytes swichPPTPage(PPTDetail parm) throws TException {
        int cur = parm.getCurPage();
        byte[] bytes = optionService.swichPPTPage(cur);
        PPTBytes pptBytes = new PPTBytes();
        pptBytes.setBytes(bytes);
        pptBytes.setPptDetail(parm);
        return pptBytes;
    }
}
