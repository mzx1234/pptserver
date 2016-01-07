package com.gdut.pptserver.utility;



import com.gdut.pptserver.application.GlobalApplication;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.List;

import com.gdut.pptserver.thrift.struct.CommonStructConstants;

import com.gdut.pptserver.constant.PptTypeConstant.PPTType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * POI解析工具类
 * Created by zison on 2015/12/31.
 */
public class POIParse {



    List<HSLFSlide> pptSlideList;
    List<XSLFSlide> pptxSlideList;
    private Dimension pgsize = null;
    private int len;


    private PPTType pptType ;

    @Autowired
    private GlobalApplication globalApplication;
    @Autowired
    private RedisUtil redisUtil;



    private  void getPPTSlides(String file) throws Exception{
        HSLFSlideShow pptSlideShow = new HSLFSlideShow(new HSLFSlideShowImpl(file));
        pgsize = pptSlideShow.getPageSize();
        pptSlideList = pptSlideShow.getSlides();
        pptType = PPTType.PPT;
        len = pptSlideList.size();
    }


    private void getPPTXSlides(String file) throws Exception{
        XMLSlideShow pptxSlideShow = new XMLSlideShow(new FileInputStream(file));
        pgsize = pptxSlideShow.getPageSize();
        pptxSlideList = pptxSlideShow.getSlides();
        pptType = PPTType.PPTX;
        len = pptxSlideList.size();
    }

    private BufferedImage getImag(int cur) {
        BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D jpgGraphics = img.createGraphics();
        // clear the drawing area

        jpgGraphics.setPaint(Color.white);
        jpgGraphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

        switch (pptType) {
            case PPT:
                pptSlideList.get(cur).draw(jpgGraphics);
                break;
            case PPTX:
                pptxSlideList.get(cur).draw(jpgGraphics);
                break;
        }
        return img;
    }


    private void toRedis() {
        for(int cur = 0; cur < globalApplication.getLen(); cur++) {
            BufferedImage img = getImag(cur);
            redisUtil.hSetObject(globalApplication.getKey(), cur+"", img);
        }
    }

    private byte[] getFromRedis(String file) {
        innitGlobal(file);
        return redisUtil.hGetBytes(globalApplication.getKey(), "0");
    }


    public byte[] parsePPTAndGetFirst(String file) throws Exception{
        byte[] result = getFromRedis(file);
        if(result != null) {
            return result;
        }
        getPPTSlides(file);
        globalApplication.setLen(len);
        toRedis();
        return redisUtil.hGetBytes(globalApplication.getKey(), "0");
    }

    public byte[] parsePPTXAndGetFirst(String file) throws Exception{
        byte[] result = getFromRedis(file);
        if(result != null) {
            return result;
        }
        getPPTXSlides(file);
        innitGlobal(file);
        toRedis();
        return redisUtil.hGetBytes(globalApplication.getKey(), "0");
    }


    private void innitGlobal(String file) {
        globalApplication.setPath(file);
        globalApplication.setFileName(file);
        globalApplication.setCurPage(0);
        globalApplication.setKey(globalApplication.getFileName());
        globalApplication.setLen(len);
    }




}
