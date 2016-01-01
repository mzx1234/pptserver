package com.gdut.pptserver.service;

import com.gdut.pptserver.BaseTest;
import com.gdut.pptserver.service.impl.ParseServiceImpl;
import org.testng.annotations.Test;

/**
 * Created by zison on 2015/12/31.
 */
public class ParseServiceTest extends BaseTest {

    @Test
    public void testParseService() throws Exception{
        ParseServiceImpl parseService = (ParseServiceImpl) getBean("parseServiceImpl");
//        parseService.parsePPTFile("C:\\Users\\zison\\Desktop\\java.ppt");
        parseService.parsePPTXFile("C:\\Users\\zison\\Desktop\\a.pptx");
        Thread.sleep(10 * 1000);
    }
}
