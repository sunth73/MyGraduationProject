package com.wmk.porject.controller.validateController;

import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.service.ValidateService;
import com.wmk.porject.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sunth
 * @DateTime 2019-04-12 10:54
 * 描述
 */
@RestController
public class ValidateController {
    @Autowired
    private ValidateService validateService;
    @RequestMapping("/validate")
    public JSONObject validateRepeat(@RequestBody JSONObject JSONobj)throws Exception{
        if(StringUtils.isEmpty(JSONobj.get("columnName"))||StringUtils.isEmpty(JSONobj.get("tableName"))||StringUtils.isEmpty(JSONobj.get("dateValue"))) {
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"参数不全");
        }
        if(StringUtils.isEmpty(JSONobj.get("tableIdName"))&&(!StringUtils.isEmpty(JSONobj.get("tableIdValue")))) {
            return ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL,"参数不全!");
        }
        String columnName=String.valueOf(JSONobj.get("columnName")).replace(";", "").replaceAll("\\s*", "");
        String tableName=String.valueOf(JSONobj.get("tableName")).replace(";", "").replaceAll("\\s*", "");
        String dateValue=String.valueOf(JSONobj.get("dateValue")).replace(";", "").replaceAll("\\s*", "");
        String tableIdName=String.valueOf(JSONobj.get("tableIdName")).replace(";", "").replaceAll("\\s*", "");
        String tableIdValue=String.valueOf(JSONobj.get("tableIdValue")).replace(";", "").replaceAll("\\s*", "");
        JSONObject resultObj = null;
        try {
            boolean b=false;
            if(!tableIdName.equals("null") && !tableIdValue.equals("null")){
                b= validateService.duplicateChecking(columnName, tableName, "'"+dateValue+"'", tableIdName, "'"+tableIdValue+"'");
            }else{
                b= validateService.duplicateChecking(columnName, tableName, "'"+dateValue+"'", null, null);
            }

            if(b) {
                resultObj=ReturnUtil.ajaxDone(ReturnUtil.CODE_SUCCESS,"该数据未存在重复，可以使用");
            }else {
                resultObj=ReturnUtil.ajaxDone(ReturnUtil.CODE_FAIL, "已存在，请重新输入");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObj;
    }
}
