package com.wmk.porject.controller.scorecontroller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.bean.Score;
import com.wmk.porject.bean.Student;
import com.wmk.porject.bean.User;
import com.wmk.porject.request.ScoreReq;
import com.wmk.porject.request.ScoreRequest;
import com.wmk.porject.service.scoreservice.ScoreService;
import com.wmk.porject.util.ReturnUtil;
import com.wmk.porject.util.SmsSample;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunth
 * @DateTime 2019-04-18 21:11
 * 描述
 */
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 查询成绩
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/scorelist")
    public JSONObject selectScoreForClass(@RequestBody Score score, HttpServletRequest httpServletRequest)throws Exception{
        JSONObject jsonObject=null;
        User user=(User) httpServletRequest.getSession().getAttribute("LoginUser");
        try {
            List<ScoreRequest>list=scoreService.selectScoreForClass(user,score.getsExamId());
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 导出成绩
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportscore")
    public void  ExportStu(@RequestBody Score score,HttpServletResponse response,HttpServletRequest httpServletRequest)throws Exception{
        User user=(User) httpServletRequest.getSession().getAttribute("LoginUser");
        List<ScoreRequest>list=scoreService.selectScoreForClass(user,score.getsExamId());

        Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("成绩","学生"),
                ScoreRequest.class, list);
        response.setHeader("content-Type","application/vnd.ms-excel");
        response.setHeader("Content-Disposition","attachment;filename="+System.currentTimeMillis()+".xls");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();

    }

    /**
     * 发送成绩
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendmessage")
    public void sendScoreForParent(@RequestBody Score score,HttpServletRequest httpServletRequest)throws Exception{
        JSONObject jsonObject=null;
        User user=(User) httpServletRequest.getSession().getAttribute("LoginUser");
        List<ScoreRequest>list=scoreService.selectScoreForClass(user,score.getsExamId());
        if(list.size()>0){
            for(ScoreRequest scoreRequest:list){
                if(!"".equals(scoreRequest.getParentPhone())){
                    String str="尊敬的"+scoreRequest.getStuName()+"的家长，您好;现将您的孩子本次考试成绩发送到您的手中";
                    String str1="语文:"+scoreRequest.getChinese()+"。数学:"+scoreRequest.getMath()+"。英语:"+scoreRequest.getEnglish();
                    String str2="。物理:"+scoreRequest.getPhysics()+"。化学:"+scoreRequest.getChemistry()+"。生物:"+scoreRequest.getBiology();
                    String str3="。地理:"+scoreRequest.getGeography()+"。历史:"+scoreRequest.getHistory()+"。政治思想:"+scoreRequest.getPolitics();
                    String str4="。总分:"+scoreRequest.getTotalScore();
                    String message=str+str1+str2+str3+str4;
                    String phoneNum=scoreRequest.getParentPhone();
                    System.out.println("======="+message);
//                    SmsSample.sendMess("",message,phoneNum);
                }
            }
        }

    }
    /**
     * 单科成绩
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/scolist")
    public JSONObject scolist(@RequestBody ScoreReq scoreReq, HttpServletRequest httpServletRequest)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<ScoreReq>list=scoreService.scorelist(scoreReq);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 全科成绩
     * @param scoreReq
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/selectScorelist")
    public JSONObject scorelist(@RequestBody ScoreRequest scoreReq, HttpServletRequest httpServletRequest)throws Exception{
        JSONObject jsonObject=null;
        try {
            List<ScoreRequest>list=scoreService.selectScorelist(scoreReq.getStuClass(),scoreReq.getsExamId());
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,list);
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDone(ReturnUtil.CODE_EXCEPTION,"访问异常");
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 打分
     * @param JSONArr
     * @return
     * @throws Exception
     */
    @RequestMapping("/update")
    public JSONObject deleteStu(@RequestBody JSONArray JSONArr)throws Exception{
        JSONObject jsonObject=null;
        List<Score> scoreList=new ArrayList<>();
        for(int i=0;i<JSONArr.size();i++){
            JSONObject JSONobj=JSONArr.getJSONObject(i);
            Score score=new Score();
            score.setRecId(JSONobj.getString("recId"));
            score.setsScore(JSONobj.getString("sScore"));
            scoreList.add(score);
        }
        try {
            int resultNum=scoreService.update(scoreList);
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_SUCCESS,"打分成功");
        } catch (Exception e) {
            jsonObject= ReturnUtil.ajaxDoneSuccess(ReturnUtil.CODE_EXCEPTION,"打分异常");
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 导出成绩
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exporfortscore")
    public void  ExportScoree(@RequestParam("flag")String flag,@RequestParam("stuClass")String stuClass,
                              @RequestParam("subCode")String subCode,@RequestParam("sExamId")String sExamId,@RequestParam("eId")String eId,
                              HttpServletResponse response)throws Exception{
        List<ScoreRequest>list=new ArrayList<>();
        List<ScoreReq>scoreReqList=new ArrayList<>();
        if("4".equals(flag)){
            list=scoreService.selectScorelist(null,sExamId);
        }else if("1".equals(flag)){
            list=scoreService.selectScorelist(stuClass,sExamId);
        }else if("2".equals(flag)){
            ScoreReq scoreReq=new ScoreReq();
            scoreReq.seteId(eId);
            scoreReq.setSubCode(subCode);
            scoreReqList=scoreService.scorelist(scoreReq);
        }else if("3".equals(flag)){
            ScoreReq scoreReq=new ScoreReq();
            scoreReq.seteId(eId);
            scoreReq.setSubCode(subCode);
            scoreReq.setStuClass(stuClass);
            scoreReqList=scoreService.scorelist(scoreReq);
        }
        Workbook workbook=null;
        String excelName=String.valueOf(System.currentTimeMillis());
        if("1".equals(flag)){
            excelName=list.get(0).geteName()+list.get(0).getStuClass()+"成绩";
            workbook= ExcelExportUtil.exportExcel(new ExportParams(list.get(0).geteName()+list.get(0).getStuClass()+"成绩","学生"),
                    ScoreRequest.class, list);
        }else if("4".equals(flag)){
            excelName=list.get(0).geteName()+"成绩";
            workbook= ExcelExportUtil.exportExcel(new ExportParams(list.get(0).geteName()+"成绩","学生"),
                    ScoreRequest.class, list);
        }else if("2".equals(flag)){
            excelName=scoreReqList.get(0).geteName()+scoreReqList.get(0).getSubName()+"单科成绩";
            workbook= ExcelExportUtil.exportExcel(new ExportParams(scoreReqList.get(0).geteName()+scoreReqList.get(0).getSubName()+"单科成绩","学生"),
                    ScoreReq.class, scoreReqList);
        }else if("3".equals(flag)){
            excelName=scoreReqList.get(0).geteName()+scoreReqList.get(0).getStuClass()+scoreReqList.get(0).getSubName()+"单科成绩";
            workbook= ExcelExportUtil.exportExcel(new ExportParams(scoreReqList.get(0).geteName()+scoreReqList.get(0).getStuClass()+scoreReqList.get(0).getSubName()+"单科成绩","学生"),
                    ScoreReq.class, scoreReqList);
        }
        response.setHeader("content-Type","application/vnd.ms-excel");
//        response.setHeader("Content-Disposition","attachment;filename="+excelName+".xls");
        response.setHeader("Content-disposition", "attachment;filename="+new String(excelName.getBytes("gbk"), "iso8859-1")+".xls");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();

    }
}
