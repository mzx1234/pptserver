package com.gdut.pptserver.utility;



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

import com.gdut.pptserver.constant.PptTypeConstant.PPTType;

/**
 * POI解析工具类
 * Created by zison on 2015/12/31.
 */
public class POIParse {



    List<HSLFSlide> pptSlideList;
    List<XSLFSlide> pptxSlideList;
    private Dimension pgsize = null;

    private PPTType pptType ;



    public  void getPPTSlides(String file) throws Exception{
        HSLFSlideShow pptSlideShow = new HSLFSlideShow(new HSLFSlideShowImpl(file));
        pgsize = pptSlideShow.getPageSize();
        pptSlideList = pptSlideShow.getSlides();
        pptType = PPTType.PPT;
    }


    public void getPPTXSlides(String file) throws Exception{
        XMLSlideShow pptxSlideShow = new XMLSlideShow(new FileInputStream(file));
        pgsize = pptxSlideShow.getPageSize();
        pptxSlideList = pptxSlideShow.getSlides();
        pptType = PPTType.PPTX;
    }

    public byte[] getImageBuffer(int cur) {
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

//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            javax.imageio.ImageIO.write(img, "jpg", out);
//            out.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return  out.toByteArray();
        return SerializeUtil.serialize(img);
    }




}
