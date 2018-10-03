package com.zjf.demo.compress;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPUtil {

    private static final String SUFFIX_NAME = "zip";

    private ZIPUtil() {
    }

    /**
     * 压缩文件
     *
     * @param zipFile  压缩文件名路径
     * @param fileList 待压缩的文件集合
     * @param isDelete 如果出现同名压缩包, 是否删除原本的压缩包, 如果false,且出现同名则抛异常
     * @return 压缩后的压缩包File
     */
    public static File compress(String zipFile, List<File> fileList, boolean isDelete) {
        File f = isCheckFileNameByZip(zipFile);
        return compress(f.getName(),
                f.getParent(),
                getFilePathArray(fileList),
                false,
                isDelete);
    }


    /**
     * 压缩文件
     *
     * @param zipFile  压缩文件名路径
     * @param fileList 待压缩的文件集合
     * @return 压缩后的压缩包File
     */
    public static File compress(String zipFile, List<File> fileList) {
        return compress(zipFile, fileList, true);
    }


    /**
     * 校验目标文件名称是否是zip格式
     *
     * @param zipFile zip文件路径
     * @return 返回File
     */
    private static File isCheckFileNameByZip(String zipFile) {
        return new File(zipFile.substring(0, isCheckFileNameInZip(zipFile)));
    }

    /**
     * 校验目标文件名称是否是zip格式
     *
     * @param zipFile zip文件路径
     * @return 返回字符串长度
     */
    private static int isCheckFileNameInZip(String zipFile) {
        int las = zipFile.lastIndexOf(".");
        if (las == -1) {
            throw new RuntimeException(zipFile + " is not zip format! this format = ??? ");
        }
        String format = zipFile.substring(las + 1);
        if (!SUFFIX_NAME.equalsIgnoreCase(format)) {
            throw new RuntimeException(zipFile + " is not zip format! this format = " + format);
        }
        return las;
    }


    private static String[] getFilePathArray(List<File> list) {
        String[] strs = new String[list.size()];
        for (int index = 0; index < strs.length; index++) {
            strs[index] = list.get(index).getPath();
        }
        return strs;
    }

    /**
     * 压缩文件
     *
     * @param zipName     压缩文件名, 无需加后缀
     * @param zipFilePath 压缩路径
     * @param filePaths   待压缩的文件路径
     * @param isNewFolder 是否在压缩包新建同名文件夹
     * @param isDeleteRes 如果出现同名压缩包, 是否删除原本的压缩包, 如果false,且出现同名则抛异常
     * @return 压缩后的压缩包File
     */
    private static File compress(String zipName, String zipFilePath, String[] filePaths,
                                 boolean isNewFolder, boolean isDeleteRes) {
        File target = null;
        File source = new File(zipFilePath);
        if (source.exists()) {
            String base = isNewFolder ? zipName + File.separator : "";
            zipName = zipName + "." + SUFFIX_NAME;
            target = new File(source.getPath(), zipName);
            if (target.exists()) {
                if (!isDeleteRes) {
                    throw new RuntimeException("Compression package name repetition !");
                }
                target.delete(); // 删除旧的文件
            }

            try (FileOutputStream fos = new FileOutputStream(target);
                 ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos))) {
                for (String fip : filePaths) {
                    addEntry(base, new File(fip), zos);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return target;
    }


    /**
     * 解压文件
     * filePath所代表文件系统不能与targetStr一致
     *
     * @param filePath  压缩文件路径
     * @param targetStr 解压至所在文件目录
     */
    public static void decompression(String filePath, String targetStr) throws FileNotFoundException {
        isCheckFileNameInZip(filePath);
        File source = new File(filePath);
        if (!source.exists()) {
            throw new FileNotFoundException("Compression package name repetition !");
        }
        ZipEntry entry = null;
        BufferedOutputStream bufferedOutputStream = null;
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(source))) {
            while ((entry = zipInputStream.getNextEntry()) != null && !entry.isDirectory()) {
                File target = new File(targetStr, entry.getName());
                if (!target.getParentFile().exists()) {
                    target.getParentFile().mkdirs();
                }
                // 写入文件
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(target));
                int read = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((read = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
                    bufferedOutputStream.write(buffer, 0, read);
                }
                bufferedOutputStream.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 扫描添加文件Entry
     *
     * @param base   基路径
     * @param source 源文件
     * @param zos    Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
        String entry = base.concat(source.getName());
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                addEntry(entry + File.separator, file, zos);
            }
        } else {

            byte[] buffer = new byte[1024 * 10];
            try (FileInputStream fis = new FileInputStream(source);
                 BufferedInputStream bis = new BufferedInputStream(fis, buffer.length)) {
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            }
        }
    }
}
