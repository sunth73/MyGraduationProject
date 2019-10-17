package com.wmk.porject.controller.studentController;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Student;
import com.wmk.porject.bean.User;
import com.wmk.porject.service.studentservice.StudentService;
import com.wmk.porject.util.ReturnUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sunth
 * @DateTime 2019-04-14 17:34
 * 描述
 */
@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/stulist")
    public JSONObject StuList(@RequestBody Student student)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<Student> studentList=studentService.stuList(student);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,studentList);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 查询学生信息
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/stuinfo")
    public JSONObject stuinfo(@RequestBody Student student)throws Exception{
        JSONObject jsonObject=null;
        try {
            Student student1=studentService.selectStu(student.getStuId());
            if(!ObjectUtils.isEmpty(student1)){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,student1);
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 查询学生信息by userId
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectByUserId")
    public JSONObject selectByUserId(@RequestBody User user)throws Exception{
        JSONObject jsonObject=null;
        try {
            Student student=studentService.selectByUserId(user.getUserid());
            if(!ObjectUtils.isEmpty(student)){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,student);
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"查询失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 添加学生信息
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/addstu")
    public JSONObject addStu(@RequestBody Student student)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=studentService.addStudent(student);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"添加成功");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"添加失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"添加异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatestu")
    public JSONObject updateStu(@RequestBody Student student)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=studentService.updateStu(student);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"修改成功");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"修改失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"修改异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 删除学生
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletestu")
    public JSONObject deleteStu(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Student> stuList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            Student student=new Student();
            student.setStuId(JSONobj.getString("stuId"));
            student.setStuUname(JSONobj.getString("stuUname"));
            stuList.add(student);
        }
        try {
            int resultNum=studentService.deleteStu(stuList);
            if(resultNum>0){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"删除成功");
            }else {
                jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"删除失败");
            }
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"删除异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 导入学生信息
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/importstu")
    public JSONObject ImportStu(@RequestPart("file")MultipartFile file)throws Exception{
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(file)) {
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL, "文件为空");
        }
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL, "上传格式错误");
        }
        JSONObject jsonObject=null;
        try {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            List<Student>list= ExcelImportUtil.importExcel(file.getInputStream(),Student.class,params);
            Map<String,Object>map=studentService.ImportStu(list);
            jsonObject=ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"map",map);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"导入异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 导出学生
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportstu")
    public void  ExportStu(@RequestParam("stuName")String stuName, HttpServletResponse response)throws Exception{
            Student student=new Student();
            student.setStuName(stuName);
            List<Student> studentList=studentService.stuList(student);
            String js=JSON.toJSONString(studentList);
        Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("学生信息","学生"),
                Student.class, studentList);
        response.setHeader("content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+new String("学生信息".getBytes("gbk"), "iso8859-1")+".xls");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();

    }

}
