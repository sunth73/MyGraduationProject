package com.wmk.porject.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.codehaus.xfire.util.Base64;

public class CommonFileUtil {
    //删除硬盘数据文件
    public static void deleteFile(String fullFilePath){
        File file = new File(fullFilePath);
        if(file.exists()){
            file.delete();
        }else{
            System.out.println(fullFilePath+"  文件不存在！");
        }
    }
    // 上传附件用的重命名方法
    public static String fileRename(String str) {
        String formatDate = new SimpleDateFormat("yyyyMMddHHmmsssss")
                .format(new Date());
        int last = str.lastIndexOf(".");
        int i = (int) (Math.random() * 1000);
        String str_head = str.substring(0, last);
        String str_type = str.substring(last);
        str = formatDate + i + str_type;
        return str;
    }

    // 上传附件用的重命名方法
    public static String fileRename(String str,String filetype) {
        String formatDate = new SimpleDateFormat("yyyyMMddHHmmsssss")
                .format(new Date());
        int last = str.lastIndexOf(".");
        int i = (int) (Math.random() * 1000);
        String str_head = str.substring(0, last);
        String str_type = str.substring(last);
        str = filetype+formatDate+i + str_type;
        return str;
    }


    //上传文件操作
    public static boolean uploadFile(File file,String filepath){
        //flag false：失败 ； true： 成功
        boolean flag = false ;
        //上传附件
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            // 建立文件输出流
            fos = new FileOutputStream(filepath);
            // 建立文件上传流
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            flag = true;
        } catch (Exception e){
            flag = false ;
            //System.out.println("文件上传失败");
            e.printStackTrace();
        } finally {
            close(fos, fis);
        }
        return flag;
    }

    //附件上传完毕后，关闭输入、输出流
    public static void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            }
            catch (IOException e){
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            }
            catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

    //下载文件操作
    public static void downLoad(HttpServletRequest request, HttpServletResponse response,
                                String filePath,String fileName) throws Exception {

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream(response.getOutputStream());

            byte[] buff = new byte[2048];
            int bytesRead;
            String outputName = URLEncoder.encode(fileName, "UTF-8");
            String agent = (String)request.getHeader("USER-AGENT");
            if(agent != null && agent.toLowerCase().indexOf("firefox") > 0){
                //outputName = "=?UTF-8?B?" + (new String(Base64.encode(fileName.getBytes("UTF-8")))) + "?=";
            }
            else{
                outputName =  java.net.URLEncoder.encode(fileName, "UTF-8");
            }
            response.setContentType("APPLICATION/OCTET-STREAM; charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + outputName + "\"");

            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
                bos.flush();
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                if(bos != null){
                    bos.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try{
                if(bis != null){
                    bis.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public static boolean isExistFile(String filepath){
        File file = new File(filepath);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    //展示图片
    public static void showPhoto(HttpServletResponse response,String filePath){
        try {
            response.setContentType("image/jpg");
            ServletOutputStream out = response.getOutputStream();
            // 将数据库中的二进制流直接写成图片到页面
            FileInputStream is = new FileInputStream(filePath);
            int i = is.available(); // 得到文件大小
            byte defaultImg[] = new byte[i];
            is.read(defaultImg); // 读数据
            is.close();
            out.write(defaultImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
