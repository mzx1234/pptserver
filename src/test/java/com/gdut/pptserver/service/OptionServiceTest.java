package com.gdut.pptserver.service;

import com.gdut.pptserver.BaseTest;
import com.gdut.pptserver.monitor.task.BaseTask;
import com.gdut.pptserver.service.impl.OptionServiceImpl;
import com.gdut.pptserver.service.impl.ParseServiceImpl;
import org.testng.annotations.Test;

/**
 * Created by zison on 2016/1/3.
 */
public class OptionServiceTest extends BaseTest {

    @Test
    public void testParseService() throws Exception{
        OptionServiceImpl optionService = (OptionServiceImpl) getBean("optionServiceImpl");
//        parseService.parsePPTFile("C:\\Users\\zison\\Desktop\\java.ppt");
        optionService.swichPPTPage(2);
        Thread.sleep(10 * 1000);
    }
}
