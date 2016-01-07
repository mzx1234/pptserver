package com.gdut.pptserver.thrift.service.processor;

import com.gdut.pptserver.thrift.service.TOptionService;
import org.springframework.stereotype.Service;

/**
 * Created by zison on 2016/1/7.
 */

public class OptionPPTServiceProcessor extends TOptionService.Processor {
    public OptionPPTServiceProcessor(TOptionService.Iface iface) {
        super(iface);
    }
}
