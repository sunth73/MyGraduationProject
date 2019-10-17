package com.wmk.porject.util.utilty;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;

public class JSONObjectUtil {
	
	public static JSONArray jsonArraySort(JSONArray src, String key,String key2) throws ParseException{
		JSONObject jsonObjectI=null;
		JSONObject jsonObjectJ=null;
		Date dateI=null;
		Date dateJ=null;
		if(ValidUtil.valid(src)){
			for (Integer i=0;i<src.size();i++){
				jsonObjectI=src.getJSONObject(i);
				if(ValidUtil.valid(key2) && ValidUtil.valid(jsonObjectI.getString(key2))){
					Date tmpDate= DateUtil.LONG_DATE_FORMAT.parse(jsonObjectI.getString(key2));
					String string=DateUtil.HMS_FORMAT.format(tmpDate);
					dateI= DateUtil.HMS_FORMAT.parse(string);
				}else if(ValidUtil.valid(jsonObjectI.getString(key))){
					dateI= DateUtil.HMS_FORMAT.parse(jsonObjectI.getString(key));
				}
				
				for(Integer j=i; j<src.size();j++){
					jsonObjectJ=src.getJSONObject(j);
					if(ValidUtil.valid(key2) && ValidUtil.valid(jsonObjectJ.getString(key2))){
						Date tmpDate=DateUtil.LONG_DATE_FORMAT.parse(jsonObjectJ.getString(key2));
						String string=DateUtil.HMS_FORMAT.format(tmpDate);
						dateJ= DateUtil.HMS_FORMAT.parse(string);
					}else if(ValidUtil.valid(jsonObjectJ.getString(key))){
						dateJ= DateUtil.HMS_FORMAT.parse(jsonObjectJ.getString(key));
					} 
					
					if(DateUtil.compareDate(dateJ, dateI, Calendar.SECOND)<0){
						JSONObject  tempJson=jsonObjectI;
						jsonObjectI=jsonObjectJ;
						dateI=dateJ;
						src.set(i, jsonObjectI);
						src.set(j, tempJson);
					}
				}
				
			}
		}
		return src;
	}
	
	//获取JSONArray中指定key值value组成的list
	public static ArrayList<String>  getAllValueListByKey(JSONArray list,String key){
		if(!ValidUtil.valid(list) || !ValidUtil.valid(key)){
			return null;
		}
		ArrayList<String> result = new ArrayList<String>();
		for(Integer index=0;index<list.size();index++){
			JSONObject object=list.getJSONObject(index);
			result.add(object.getString(key));
		
		}
		return result;
		
	}
	
	//获取指定JSONArray，返回指定key1相等的value的key2值
	public static String getValueByKeyOnKey1OnArray(JSONArray list,String key1,String value1,String key2)throws Exception{
		if (!ValidUtil.valid(key1)||!ValidUtil.valid(key1)||!ValidUtil.valid(value1)||!ValidUtil.valid(key2)) {
			return null;
		}
		for (int index = 0; index < list.size();index ++) {
			if (value1.equals(list.getJSONObject(index).getString(key1))) {
				return list.getJSONObject(index).getString(key2);
			}
		}
		return null;	
	}
	/**
	 * 连接两个JSONArray为一个JSONArray
	 * @return
	 */
	public static JSONArray combineJsonArrays(JSONArray array1,JSONArray array2) {
		if(ValidUtil.valid(array1) && !ValidUtil.valid(array2)){
			return array1;
		}else if(ValidUtil.valid(array2) && !ValidUtil.valid(array1)){
			return array2;
		}else if(ValidUtil.valid(array1) && ValidUtil.valid(array2)){
			for(Integer index=0;index<array2.size();index++){
				JSONObject object=array2.getJSONObject(index);
				array1.add(object);
			}
			return array1;
		}else{
			return null;
		}
	}
	
	/**
	 * 连接两个JSONArray为一个指定length的JSONArray
	 * @return
	 */
	public static JSONArray combineJsonArrays(JSONArray array1,JSONArray array2,Integer length) {
		if(ValidUtil.valid(length)){
			if(ValidUtil.valid(array1) && !ValidUtil.valid(array2)){
				return array1;
			}else if(ValidUtil.valid(array2) && !ValidUtil.valid(array1)){
				return array2;
			}else if(ValidUtil.valid(array1) && ValidUtil.valid(array2)){
				for(Integer index=0;index<array2.size();index++){
					if(length>array1.size()){
						JSONObject object=array2.getJSONObject(index);
						array1.add(object);
					}else{
						break;
					}
					
				}
				return array1;
			}
		}
		return null;
	}
	
	/**
	 * 改变JSONArray中指定值的JSONObject
	 * @return
	 */
	public static JSONArray changeSpecifyKeysJsonObjectFromArray(JSONArray list,String key,Integer value,String key1,Object object) {
		if(ValidUtil.valid(list)){
			for(Integer index=0; index<list.size();index++){
				JSONObject object2=list.getJSONObject(index);
				if(object2.containsKey(key) && object2.getInteger(key).equals(value)){
					object2.put(key1, object);
					list.remove(index);
					list.add(object2);
					return list;
				}
			}
		}
		return null;
	}
	/**
	 * 获取JSONArray中指定值的JSONObject
	 * @return
	 */
	public static JSONObject getSpecifyKeysJsonObjectFromArray(JSONArray list,String key,String value) {
		if(ValidUtil.valid(list)){
			for(Integer index=0; index<list.size();index++){
				JSONObject object=list.getJSONObject(index);
				if(object.containsKey(key) && object.getString(key).equals(value)){
					return object;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取JSONArray中指定值的JSONObject
	 * @return
	 */
	public static JSONObject getSpecifyKeysJsonObjectFromArray(JSONArray list,String key,Integer value) {
		if(ValidUtil.valid(list)){
			for(Integer index=0; index<list.size();index++){
				JSONObject object=list.getJSONObject(index);
				if(object.containsKey(key) && ValidUtil.valid(object.getInteger(key)) &&object.getInteger(key).equals(value)){
					return object;
				}
			}
		}
		return null;
	}
	
	/**
	 * 将JSONObject中的key1的Int转换成key2的list
	 * @return
	 */
	public static JSONObject changeKeyAndValue(JSONObject object,String key1,String key2){
		
		if(!ValidUtil.valid(object) || !object.containsKey(key1)){
			return object;
		}
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(object.getInteger(key1));
		
		object.put(key2,list);
		
		return object;
	}
	
	/**
	 * 将JSON数组转换为指定class的list
	 * @param
	 * @param
	 * @return
	 */
	public static <T> ArrayList<T> jsonArrayToList(JSONArray source, Class<T> clazz){
		if (!ValidUtil.valid(source) || !ValidUtil.valid(clazz)) {
			return null;
		}
		ArrayList<T> result = new ArrayList<T>();
		for(Integer index=0;index<source.size();index++){
			JSONObject object=source.getJSONObject(index);
			result.add(jsonToBean(object, clazz));
		}
		return result;
		
	}
	
	/**
	 * 将list转换为JSON数组
	 * @param objList
	 * @return
	 */
	public static JSONArray ListToJSONArray(Object objList) {
		if (!ValidUtil.valid(objList)) {
			return null;
		}
		return JSONArray.parseArray(JSONArray.toJSONString(objList));
	}
	
	/**
	 * 实体转换为JSON
	 * @param obj
	 * @return
	 */
	public static JSONObject entityToJSON(Object obj) {
		if (!ValidUtil.valid(obj)) {
			return null;
		}
		return (JSONObject) JSONObject.parse(JSONObject.toJSONString(obj,SerializerFeature.WriteMapNullValue));
	}
	
	/**
	 * String 转换为JSON
	 * @param obj
	 * @return
	 */
	public static JSONObject entityToJSON(String obj) {
		if (!ValidUtil.valid(obj)) {
			return null;
		}
		return (JSONObject) JSONObject.parse(obj);
	}
	
	//保留服务返回结果的有效部分
	public static JSONObject jsonToValidJson(String[] keys, JSONObject originJson) {
		if (null!=originJson) {
			JSONObject returnJson=new JSONObject();
			for (String key:keys) {
				returnJson.put(key,originJson.get(key));
			}
			return returnJson;
		}
		return null;
	}
	
	//保留服务返回结果的有效部分
		public static Map mapToValidMap(String[] keys, Map originMap) {
			if (null!=originMap) {
				Map returnMap=new HashMap();
				for (String key:keys) {
					returnMap.put(key,originMap.get(key));
				}
				return returnMap;
			}
			return null;
		}
	
	//删除服务多余的信息
	public static JSONObject jsonRemoveKeys(String[] keys, JSONObject originJson) {
		if (null!=originJson) {
			for (String key:keys) {
				if(originJson.containsKey(key)){
					originJson.remove(key);
				}
			}
		}
		return originJson;
	}
	
	//json转bean
	public static <T> T jsonToBean(JSONObject json,  Class<T> clazz) {
		if(!ValidUtil.valid(json) || !ValidUtil.valid(clazz)){
			return null;
		}
		String str=json.toJSONString();
		return new Gson().fromJson(str,  clazz);
	}
	
    /**
     * 判断JSONObject所有key都为null
     * @param src
     * @return
     */
    public final static boolean isNullValue(JSONObject src) {
    	Set<Entry<String,  Object>> entries = src.entrySet();  
    	for (Entry<String,  Object> entry:entries) {
    		if (ValidUtil.valid(entry.getValue())) {
    			return false;
    		}
    	}
        return true;
    }
	
	public static void main(String[] args) {
/*		SysUser sysUser = new SysUser();
		sysUser.setId(2222);
		sysUser.setUser_name("hahaha");
		System.out.println(entityToJSON(sysUser));
		SysUser sysUser1 = new SysUser();
		sysUser1.setId(3333);
		sysUser1.setUser_name("hehehehe");		
		List<SysUser> userList = new ArrayList<SysUser>();
		userList.add(sysUser);
		userList.add(sysUser1);
		System.out.println(ListToJSONArray(userList));*/
	}
}
