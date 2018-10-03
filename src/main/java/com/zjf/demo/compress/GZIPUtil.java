package com.zjf.demo.compress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtil {

    private static final int BUFFER_SIZE = 1024 * 1024 * 5;


    /**
     * 开始压缩文件到一个.gz包中  而这里的.gz之前的文件名可与之前不一致，如下面的文件名，
     * 使用解压工具看到里面的文件是去掉.gz之前的部分myTestFile001.txt
     *
     * @param filePath   F:\\MyTest\\myTestFile.txt         压缩文件路径
     * @param enFilePath F:\\MyTest\\myTestFile001.txt.gz   生成的文件路径
     */
    public void fileToGzip(String filePath, String enFilePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(enFilePath);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
             FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] b = new byte[BUFFER_SIZE];
            int length = 0;
            while ((length = fileInputStream.read(b)) != -1) {
                gzipOutputStream.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * gzip文件解压方法
     *
     * @param filePath   解压文件路径
     * @param enFilePath 解压后的文件路径
     */
    public void gzipToFile(String filePath, String enFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(enFilePath)) {
            int num;
            byte[] buf = new byte[BUFFER_SIZE];
            while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1) {
                fileOutputStream.write(buf, 0, num);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
