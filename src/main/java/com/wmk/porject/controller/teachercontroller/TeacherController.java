package com.wmk.porject.controller.teachercontroller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Student;
import com.wmk.porject.bean.Teacher;
import com.wmk.porject.bean.User;
import com.wmk.porject.service.teacherservice.TeacherService;
import com.wmk.porject.util.ReturnUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sunth
 * @DateTime 2019-04-16 22:04
 * 描述
 */
@RestController
@RequestMapping("/tea")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("/tealist")
    public JSONObject TeaList(@RequestBody Teacher teacher)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<Teacher> teacherList=teacherService.teaList(teacher);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,teacherList);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"查询异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 查询教师信息
     * @param teacher
     * @return
     * @throws Exception
     */
    @RequestMapping("/teainfo")
    public JSONObject teaInfo(@RequestBody Teacher teacher)throws Exception{
        JSONObject jsonObject=null;
        try {
            Teacher teacher1=teacherService.selectTea(teacher.getTeaId());
            if(!ObjectUtils.isEmpty(teacher1)){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,teacher1);
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
     * 查询教师信息by userId
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectByUserId")
    public JSONObject selectByUserId(@RequestBody User user)throws Exception{
        JSONObject jsonObject=null;
        try {
            Teacher teacher=teacherService.selectTeacherByUserId(user.getUserid());
            if(!ObjectUtils.isEmpty(teacher)){
                jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,teacher);
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
     * @param teacher
     * @return
     * @throws Exception
     */
    @RequestMapping("/addtea")
    public JSONObject addTea(@RequestBody Teacher teacher)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=teacherService.addTeacher(teacher);
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
     * 修改教师信息
     * @param teacher
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatetea")
    public JSONObject updateTea(@RequestBody Teacher teacher)throws Exception{
        JSONObject jsonObject=null;
        try {
            int resultNum=teacherService.updateTea(teacher);
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
    @RequestMapping("/deletetea")
    public JSONObject deleteTea(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Teacher> teaList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            Teacher teacher=new Teacher();
            teacher.setTeaId(JSONobj.getString("teaId"));
            teacher.setTeaLoginName(JSONobj.getString("teaLoginName"));
            teaList.add(teacher);
        }
        try {
            int resultNum=teacherService.deleteTea(teaList);
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
     * 导入教师信息
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/importtea")
    public JSONObject ImportTea(@RequestPart("file") MultipartFile file)throws Exception{
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
            List<Teacher> list= ExcelImportUtil.importExcel(file.getInputStream(),Teacher.class,params);
            String js= JSON.toJSONString(list);
            System.out.println("======"+js);
            Map<String,Object> map=teacherService.ImportTea(list);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"map",map);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"导入异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 导出教师信息
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exporttea")
    public void  ExportTea(@RequestParam("teaName")String teaName, HttpServletResponse response)throws Exception{
        Teacher teacher=new Teacher();
        teacher.setTeaName(teaName);
//        teacher.setTeaCalss(teaCalss);
        List<Teacher> teacherList=teacherService.teaList(teacher);
        String js= JSON.toJSONString(teacherList);
        Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("教师信息","教师"),
                Teacher.class, teacherList);
        response.setHeader("content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+new String("教师信息".getBytes("gbk"), "iso8859-1")+".xls");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();

    }
}
