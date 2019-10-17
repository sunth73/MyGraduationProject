package com.wmk.porject.file;

import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Student;
import com.wmk.porject.bean.Teacher;
import com.wmk.porject.bean.User;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.service.teacherservice.TeacherService;
import com.wmk.porject.service.userservice.UserService;
import com.wmk.porject.util.CommonFileUtil;
import com.wmk.porject.util.FileUtils;
import com.wmk.porject.util.RandomUtil;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author Sunth
 * @DateTime 2019-05-25 17:55
 * 描述
 */
@RestController
@RequestMapping("/file")
public class fileController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Value("${Imagepath}")
    private String path;
    @RequestMapping(value = "/fileUpload")
    public JSONObject uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) throws Exception{
        User user= (User) request.getSession().getAttribute("LoginUser");
        if (file == null || file.isEmpty()) {
            return ReturnUtil.ajaxDoneNullError("上传文件为空");
        }
        File paths = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!paths.exists()) {
            paths = new File("");
        }
        File upload = new File(paths.getAbsolutePath(),path);
        if(!upload.exists()) {
            upload.mkdirs();
        }
        JSONObject resultObj = null;
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
//	        String filePath = request.getSession().getServletContext().getRealPath(path);
        String filePath=upload.getAbsolutePath()+"/";
        String fileTranscodingName = CommonFileUtil.fileRename(fileName, "");
        if(user.getRoleIds().contains("9")){
            Student student=studentService.selectByUserId(user.getUserid());
            CommonFileUtil.deleteFile(ClassUtils.getDefaultClassLoader().getResource("").getPath()+student.getStuPhotoUrl());
        }else  if(user.getRoleIds().contains("4")){
            Teacher teacher=teacherService.selectTeacherByUserId(user.getUserid());
            CommonFileUtil.deleteFile(ClassUtils.getDefaultClassLoader().getResource("").getPath()+teacher.getTeaPhoteUrl());
        }
        try {
            FileUtils.upload(file.getInputStream(),file.getBytes(), filePath, fileTranscodingName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imgPath = path+fileTranscodingName;
        if(user.getRoleIds().contains("9")){
            Student student=studentService.selectByUserId(user.getUserid());
            student.setStuPhotoUrl(imgPath);
            studentService.updateStu(student);
        }else  if(user.getRoleIds().contains("4")){
            Teacher teacher=teacherService.selectTeacherByUserId(user.getUserid());
            teacher.setTeaPhoteUrl(imgPath);
            teacherService.updateTea(teacher);
        }
        return resultObj = ReturnUtil.ajaxDoneSuccess("文件上传成功", "fileUrl", imgPath );
    }
    /**
     * 方法名：showImage
     * 描    述：展示图片
     * @param
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/showImage", method = RequestMethod.GET)
    public void showImage(@RequestParam("imageSrc")String imageSrc,HttpServletResponse response) throws Exception {
        //根据FileId 获取原文件信息。
        //获取原文件的完整存放路径
        String fullFilePath =ClassUtils.getDefaultClassLoader().getResource("").getPath()+imageSrc;
        System.out.println("========="+fullFilePath);
//		String fullFilePath =request.getSession().getServletContext().getRealPath(path)+commRecordsFile.getFileName();
        CommonFileUtil.showPhoto(response, fullFilePath);
    }
}
