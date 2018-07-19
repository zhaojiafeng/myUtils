package com.zjf.demo.qrcode;

import com.github.binarywang.utils.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeDecoderHandler {


    public static String zxingCodeAnalyze(String analyzePath) throws Exception {
        BufferedImage bufferedImage;

        try {
            bufferedImage = ImageIO.read(new File(analyzePath));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);

            return result.getText();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * <span style="font-size:18px;font-weight:blod;">QRCode二维码解析</span>
     * @param codePath    二维码路径
     * @return 解析结果
     */
//    public static String QRCodeAnalyze(String codePath) {
//        File imageFile = new File(codePath);
//        BufferedImage bufImg = null;
//        String decodedData = null;
//        try {
//            if(!imageFile.exists())
//                return "二维码不存在";
//            bufImg = ImageIO.read(imageFile);
//
//            QRCodeDecoder decoder = new QRCodeDecoder();
//            decodedData = new String(decoder.decode(new ImageUtil(bufImg)), "gb2312");
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        } catch (DecodingFailedException dfe) {
//            System.out.println("Error: " + dfe.getMessage());
//            dfe.printStackTrace();
//        }
//        return decodedData;
//    }

}
