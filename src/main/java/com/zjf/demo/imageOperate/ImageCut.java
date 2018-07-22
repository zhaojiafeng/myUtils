package com.zjf.demo.imageOperate;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageCut {

    public static void cut(String sourcePath, int x1, int y1, int width, int height, String descpath) {
        FileInputStream is = null;
        ImageInputStream iis = null;
        try {
            is = new FileInputStream(sourcePath);
            String fileSuffix = sourcePath.substring(sourcePath.lastIndexOf(".") + 1);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(fileSuffix);
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x1, y1, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, fileSuffix, new File(descpath));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                is = null;
                iis = null;
            }
        }
    }


}
