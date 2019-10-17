package com.wmk.porject.util;

import com.alibaba.fastjson.JSONObject;
import com.wmk.porject.util.entity.IHPResp;
import com.wmk.porject.util.entity.IHPRespHeader;
import com.wmk.porject.util.utilty.JSONObjectUtil;
import com.wmk.porject.util.utilty.ValidUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回信息的统一处理
 * 
 * @author tangtian
 * @date 2016年6月28日
 */
public class ReturnUtil {
	/* 返回码key */
	public static final String RETURN_CODE_NAME = "resultCode";

	/* 返回提示信息key */
	public static final String RETURN_MSG_NAME = "resultDesc";
	
	/* 错误代码信息key */
	public static final String RETURN_ERROR_CODE_NAME = "errorCode";
	/* 错误信息key */
	public static final String RETURN_ERROR_MSG_NAME = "errorInfo";

	/* 返回数据key */
	public static final String RETURN_DETAILS_NAME = "body";
	public static final String RETURN_DATA_NAME = "data";
	public static final String RETURN_HEADER_NAME = "header";
	
	public static final String CODE_SUCCESS 			= "0"; 		/* 返回码 成功 */
	public static final String CODE_FAIL	 = "1"; 		/* 返回码 失败 */
	public static final String CODE_EXCEPTION ="2"; /*操作异常*/
	public static final String CODE_ONE ="3"; /*操作异常*/
	public static final String CODE_TWO ="4"; /*操作异常*/
	public static final String SUCCESS_DESC	 = "操作成功";
	public static final String FAIL_DESC	 = "操作失败";
	
	// --------------------------- 系统通用错误编码 :以 -1 开头------------------------------------
	public static final String CODE_TOKEN_ERROR	    = "-101";		/*返回码  token错误*/
	public static final String CODE_VERIFY_CODE_ERROR  = "-102";		/*返回码  验证码错误*/
	public static final String CODE_VERIFY_CODE_EXPIRE = "-103";		/*返回码  验证码过期*/
	public static final String CODE_NULL_ERROR 		= "-104"; 	/* 返回码 参数为空错误 */
	public static final String CODE_FORMAT_ERROR 		= "-105"; 	/* 返回码 参数格式错误 */
	public static final String CODE_QUERY_ERROR 		= "-106"; 	/* 返回码参数查询单个返回多个错误 */
	public static final String CODE_INTERNAL_ERROR		= "-107";     /* 服务器内部错误 */
	// --------------------------- 用户管理部分错误编码：以-2开头 --------------------------------------
	public static final String CODE_USER_NOT_EXIST	= "-201"; /*返回码  用户不存在 */
	public static final String CODE_INVALID_USER	= "-202"; /*返回码  用户失效 */
	public static final String CODE_PASSWORD_ERROR	= "-203"; /*返回码  用户名密码错误 */
	public static final String CODE_VERIFY_USER_PRIVILEGE	= "-204"; /*返回码  用户无权限 */
	
	// --------------------------- 医院管理部分错误编码：以-3开头 --------------------------------------	
	// --------------------------- 科室管理部分错误编码：以-4开头 --------------------------------------
	// --------------------------- 应用管理部分错误编码：以-5开头 --------------------------------------
	// --------------------------- 角色权限管理部分错误编码：以-6开头 --------------------------------------
	// --------------------------- 菜单管理部分错误编码：以-7开头 --------------------------------------
	// --------------------------- 字典部分错误编码：以-8开头 --------------------------------------
	// --------------------------- 参数管理部分错误编码：以-9开头 --------------------------------------
	// --------------------------- 会诊管理部分错误编码：以-10开头 --------------------------------------
	// --------------------------- 会议管理部分错误编码：以-11开头 --------------------------------------
	public static final String CODE_VIDEO_ERROR 	= "-1101"; 	/* 视频会议创建失败错误 */
	
	
	/**
	 * 构建返回json
	 * 
	 * @param code
	 * @param message
	 * @return
	 */
	public static JSONObject ajaxDone(String code,  String message) {
		IHPResp resp = new IHPResp();
		IHPRespHeader header = resp.getHeader();
		if (code.trim().equals(CODE_SUCCESS)){
			header.setResultCode(CODE_SUCCESS);
			header.setResultDesc(message);
			header.setErrorCode(null);
			header.setErrorInfo("");
		}else{
			header.setResultCode(code);
			header.setResultDesc(message);
			header.setErrorCode(code);
			header.setErrorInfo(message);
		}	
		
		return JSONObjectUtil.entityToJSON(resp);
		
	}

	/**
	 * 构建返回json 并带数据
	 * 
	 * @param code
	 * @param message
	 * @param details
	 * @return
	 */
	public static JSONObject ajaxDone(String code,  String message, 
			Object details) {
		IHPResp resp = new IHPResp();
		IHPRespHeader header = resp.getHeader();
		if (code.trim().equals(CODE_SUCCESS)){
			header.setResultCode(CODE_SUCCESS);
			header.setResultDesc(message);
			header.setErrorCode("");
			header.setErrorInfo("");
		}else{
			String errStrs = "";
			if (details instanceof BindingResult) {
				BindingResult result = (BindingResult) details;
				if (result.hasErrors()) {
					List<ObjectError> list = result.getAllErrors();
					for (ObjectError error : list) {
						errStrs += error.getDefaultMessage();
					}
				}
				details = new Object();
			}
			header.setResultCode(CODE_FAIL);
			header.setResultDesc(message);
			header.setErrorCode(code);
			header.setErrorInfo(errStrs);
		}	
		resp.setBody(details);
		
		return JSONObjectUtil.entityToJSON(resp);
	}
	
	public static JSONObject ajaxAppSrvDone(String retStr) {
		return JSONObjectUtil.entityToJSON(retStr);
	}
	
	/**
	 * 构建返回json 并带校验提示信息
	 * 
	 * @param code
	 * @param message
	 * @param result
	 * @return
	 
	public static JSONObject ajaxDone(int code,  String message, BindingResult result) {
		List<ObjectError> allErrors = result.getAllErrors();
		for (ObjectError each : allErrors) {
			message += each.getDefaultMessage();
		}
		JSONObject mav = new JSONObject();
		mav.put(RETURN_CODE_NAME,  code);
		mav.put(RETURN_MSG_NAME,  message);
		return mav;
	}
	*/

	/**
	 * 成功
	 * 
	 * @param message
	 * @return
	 */
	public static JSONObject ajaxDoneSuccess(String message) {
		return ajaxDone(CODE_SUCCESS,  message);
	}
	
	
	/**
	 * 成功
	 * 
	 * @param message
	 * @return
	 */
	public static JSONObject ajaxDoneNoPrivilegeError(String message) {
		return ajaxDone(CODE_VERIFY_USER_PRIVILEGE, message);
	}
	/**
	 * 成功带数据
	 * 
	 * @param message
	 * @return
	 */
	public static JSONObject ajaxDoneSuccess(String message,  Object details) {
		return ajaxDone(CODE_SUCCESS,  message,  details);
	}

	/**
	 * 成功带数据
	 * 
	 * @param message
	 * @return
	 */
	public static JSONObject ajaxDoneSuccess(String message,  String keyName, 
			Object details) {
		Map<String,  Object> data = new HashMap<String,  Object>();
		data.put(keyName,  details);
		return ajaxDone(CODE_SUCCESS,  message,  data);
	}

	
	/**
	 * 参数后台验证码错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneVerifyCodeError(String message) {
		return ajaxDone(CODE_VERIFY_CODE_ERROR,  message);
	}
	
	/**
	 * 参数后台token错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneTokenError(String message) {
		return ajaxDone(CODE_TOKEN_ERROR,  message);
	}
	


	/**
	 * 参数格式错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneFormatError(String message) {
		return ajaxDone(CODE_FORMAT_ERROR,  message);
	}

	/**
	 * 参数格式错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneFormatError(String message, 
			String details) {
		return ajaxDone(CODE_FORMAT_ERROR,  message,  details);
	}

	/**
	 * 参数为空错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneNullError(String message) {
		return ajaxDone(CODE_NULL_ERROR,  message);
	}

	/**
	 * 参数为空错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneNullError(String message, String details) {
		return ajaxDone(CODE_NULL_ERROR,  message, details);
	}

	/**
	 * 后台异常
	 * 
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDoneException(Exception e) {
		System.out.println(e.getMessage());
		return ajaxDone(CODE_INTERNAL_ERROR,  "服务器内部错误");
	}
	
	/* 参数为空错误
	 * 
	 * @param message
	 * @param e
	 * @return
	 */
	public static JSONObject ajaxDonePrivilegeError(String message) {
		return ajaxDone(CODE_VERIFY_USER_PRIVILEGE,  message);
	}

	/**
	 * 判定返回的结果是否成功
	 * 
	 * @param result
	 * @return
	 */
	public static Boolean isSuccess(JSONObject result) {

		if  (!ValidUtil.valid(result)) {
			return false;
		}
		if  (!result.containsKey(RETURN_CODE_NAME)) {
			return false;
		}
		if  (!CODE_SUCCESS.equals(result.getString(RETURN_CODE_NAME))) {
			return false;
		}
		return true;
	}
	
	/**
	 * 删除返回结果的details
	 * 
	 * @param result
	 * @return
	 */
	public static JSONObject removeDetails(JSONObject result) {
		if  (result.containsKey("details")) {
			result.remove("details");
		}
		return result;
	}
	
	/**
	 * 判定返回的结果是否成功
	 * 
	 * @param result
	 * @return
	 */
	public static Boolean isSuccess(JSONObject result, String key) {

		if  (!ValidUtil.valid(result)) {
			return false;
		}
		if  (!result.containsKey(RETURN_DETAILS_NAME)) {
			return false;
		}
		if  (!CODE_SUCCESS.equals(result.getInteger(RETURN_CODE_NAME))) {
			return false;
		}
		if  ( null==result.getJSONObject(RETURN_DETAILS_NAME).get(key)) {
			return false;
		}
		return true;
	}

	public static JSONObject ajaxDoneSuccessDetails(String message, 
			Object details) {
		JSONObject ret = ajaxDone(CODE_SUCCESS,  message);
		ret.put("details",  details);
		return ret;
	}
	
	/**
	 * 构建返回json 并带数据
	 * 
	 * @param code
	 * @param message
	 * @param details
	 * @return
	 */
	public static JSONObject ajaxDone(int code, String message,	BindingResult result) {
		JSONObject mav = new JSONObject();
		mav.put(RETURN_CODE_NAME, code);
		mav.put(RETURN_MSG_NAME, message);
		List<String> errStrs = new ArrayList<String>();
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				errStrs.add(error.getDefaultMessage());
			}
		}
		mav.put(RETURN_DATA_NAME, errStrs);
		return mav;
	}
}
