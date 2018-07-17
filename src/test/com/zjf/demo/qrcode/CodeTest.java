package com.zjf.demo.qrcode;

import static org.junit.Assert.*;

public class CodeTest {


    public void test() {

    /**
    *    QRcode 二维码生成测试
    */
    QRCodeEncoderHandler.QRCodeCreate(
            "http://blog.csdn.net/u014266877",
            "E://qrcode.jpg",
            15,
            "E://icon.png");

    /**
     *     QRcode 二维码解析测试
     */
     String qrcodeAnalyze = QRCodeDecoderHandler.QRCodeAnalyze("E://qrcode.jpg");
    /**
     * ZXingCode 二维码生成测试
     */
     QRCodeEncoderHandler.zxingCodeCreate("http://blog.csdn.net/u014266877",
             300,
             300,
             "E://zxingcode.jpg",
             "jpg");
    /**
     * ZxingCode 二维码解析
     */
        try {
            String zxingAnalyze =  QRCodeDecoderHandler.zxingCodeAnalyze("E://zxingcode.jpg").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("success");
    }



}