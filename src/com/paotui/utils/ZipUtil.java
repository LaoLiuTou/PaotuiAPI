package com.paotui.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipUtil {
	
	/**
     * 
     * @param zipFile  压缩包文件对象
     * @param listKey  压缩的图片物理地址
     * @return
     */
    public static boolean packageZip(File zipFile,List<String> listKey){
    	Logger logger = Logger.getLogger("FileUSLogger"); 
        //图片打包操作
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        try {
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));// 用这个构造最终压缩包的输出流
//            zipSource = null;// 将源头文件格式化为输入流
            
            for (String picKey : listKey) {
                
                File file = new File(picKey);
                logger.info("uppic zipFile: " + picKey );
                zipSource = new FileInputStream(file);

                byte[] bufferArea = new byte[1024 * 10];// 读写缓冲区

                // 压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zipStream.putNextEntry(zipEntry);// 定位到该压缩条目位置，开始写入文件到压缩包中

                bufferStream = new BufferedInputStream(zipSource, 1024 * 10);// 输入缓冲流
                int read = 0;

                // 在任何情况下，b[0] 到 b[off] 的元素以及 b[off+len] 到 b[b.length-1]
                // 的元素都不会受到影响。这个是官方API给出的read方法说明，经典！
                while ((read = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
                    zipStream.write(bufferArea, 0, read);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            logger.error("zipStream下载文件报错：", e);
            return false;
        } finally {
            // 关闭流
            try {
                if (null != bufferStream)
                    bufferStream.close();
                if (null != zipStream)
                    zipStream.close();
                if (null != zipSource)
                    zipSource.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                logger.error("close stream下载文件报错：", e);
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
    	//test code
    	/*List<String> listKey = new ArrayList<String>();
        listKey.add("D:/2Dcode/dest.gif");
        listKey.add("D:/2Dcode/dest.jpg");
        
        java.io.File zipFile = new java.io.File("D:/2Dcode/DownLoad4.zip");// 最终打包的压缩包
        System.out.println("zipFile exists: " + zipFile.exists());
        System.out.println("zipFile size: " + zipFile.length());
        packageZip(zipFile,listKey);
        System.out.println("zipFile exists2: " + zipFile.exists());
        System.out.println("zipFile size: " + zipFile.length());*/
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    	System.out.println(new Random().nextInt(1000));
    }
}
