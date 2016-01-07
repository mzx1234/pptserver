package com.gdut.pptserver.thrift.service.processor;

import com.gdut.pptserver.thrift.service.TParsePPTService;
import org.springframework.stereotype.Service;


/**
 * Created by zison on 2016/1/7.
 */

public class ParserPPTServiceProcessor  extends TParsePPTService.Processor{

    public ParserPPTServiceProcessor(TParsePPTService.Iface iface) {
        super(iface);
    }

}
