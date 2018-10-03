package com.zjf.demo.compress;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressFileUtil {

    private static final int BUFFER_SIZE = 2 * 1024;

    public static void fileToZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws FileNotFoundException {
        File file = new File(srcDir);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            compress(file, zos, file.getName());
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }


    /**
     * 压缩成ZIP 方法2
     *
     * @param srcFiles 需要压缩的文件列表
     * @param out      压缩文件输出流
     */
    public static void fileToZip(List<File> srcFiles, OutputStream out) {
        try (ZipOutputStream zos = new ZipOutputStream(out);) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,
     *                         true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name) {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(sourceFile)) {
                zos.putNextEntry(new ZipEntry(name));
                int len;
                while ((len = fileInputStream.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                try {
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    zos.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                for (File file : listFiles) {
                    compress(file, zos, name + "/" + file.getName());
                }
            }
        }
    }
}
