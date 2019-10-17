package com.wmk.porject.util;

/**
 * @author Sunth
 * @DateTime 2019-05-25 17:58
 * 描述
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * 文件上传工具包
 */
public class FileUtils {

    /**
     *
     * @param file 文件
     * @param filePath 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static void upload(InputStream inputStream,byte[] file, String filePath, String fileName) throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        int bytesum = 0;
        int byteread = 0;
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        file = new byte[1444];
        while ((byteread = inputStream.read(file)) != -1) {
            bytesum += byteread;
            out.write(file, 0, byteread);
        }
        out.flush();
        out.close();

//        // 生成新的文件名
//        //String realPath = path + "/" + FileNameUtils.getFileName(fileName);
//
//        //使用原文件名
//        String realPath = path + "/" + fileName;
//
//        File dest = new File(realPath);
//
//        //判断文件父目录是否存在
//        if(!dest.isDirectory()){
//            dest.mkdirs();
//        }
//
//        try {
//            //保存文件
//            file.transferTo(dest);
//            return true;
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }

    }
    //删除硬盘数据文件
    public static void deleteImages(String fullFilePath){
        File file = new File(fullFilePath);
        if(file.exists()){
            file.delete();
        }else{
            System.out.println(fullFilePath+"  文件不存在！");
        }
    }
//    public JSONObject delFile(@RequestBody JSONObject JSONobj,HttpServletRequest request) throws Exception {
//		String fileId = String.valueOf(JSONobj.get("fileId") == null ? "":JSONobj.get("fileId"));
//		//根据FileId 获取原文件信息。
//		//获取原文件的完整存放路径
////		String fullFilePath =fileBaseSavePath+commRecordsFile.getFileName();
//		String fullFilePath =request.getSession().getServletContext().getRealPath(path)+commRecordsFile.getFileName();
//		//删除原文件
//		CommonFileUtil.deleteFile(fullFilePath);
//		commrecordsFileService.deleteCommrecordsFile(fileId);
//		return ReturnUtil.ajaxDone(ReturnUtil.CODE_SUCCESS, "删除成功");
//	}

}

