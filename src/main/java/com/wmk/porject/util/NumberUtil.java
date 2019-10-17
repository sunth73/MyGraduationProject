package com.wmk.porject.util;

/**
 * @author Sunth
 * @DateTime 2019-04-15 10:11
 * 描述
 */
public class NumberUtil {
    public static String getNum(String str) {
        str=str.trim();
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        return str2;
    }
}
